package org.example.demo.streams;

import io.confluent.kafka.serializers.KafkaAvroSerializerConfig;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerializer;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.state.HostInfo;
import org.example.demo.avro.Alert;
import org.example.demo.avro.Notification;
import org.example.demo.avro.Rule;
import org.example.demo.bean.AlertDlq;
import org.example.demo.bean.AlertRule;
import org.example.demo.serdes.CustomSerdes;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

@Component
public class LeftJoinKafkaStreams {

    public static final String ALERT_TOPIC = "alert";
    public static final String RULE_TOPIC = "rule";
    public static final String REPARTITION_TOPIC = "alert-repartition";
    public static final String NOTIFICATION_TOPIC = "notification";
    public static final String DLQ_TOPIC = "alert-dlq";
    public static final String RULE_STATE_STORE = "rule-store";

    private KafkaStreams streams;
    private HostInfo hostInfo;

    private String bootstrapServer;
    private String applicationId;
    private String restEndpointHostname;
    private int restEndpointPort;
    private int numberPartitionAlert;
    private int numberPartitionRule;
    private int numberPartitionNotification;

    public LeftJoinKafkaStreams(){

        bootstrapServer = getEnvOrDefault("KAFKA_BOOTSTRAP_SERVER", "localhost:9092");
        applicationId = getEnvOrDefault("APPLICATION_ID", "event-notification-streams");
        restEndpointHostname = getEnvOrDefault("REST_ENDPOINT_HOSTNAME", "localhost");
        restEndpointPort = Integer.parseInt(getEnvOrDefault("REST_ENDPOINT_PORT", "8080"));
        hostInfo = new HostInfo(restEndpointHostname, restEndpointPort);

        numberPartitionAlert = Integer.parseInt(getEnvOrDefault("NUMBER_PARTITION_ALERT", "3"));
        numberPartitionRule = Integer.parseInt(getEnvOrDefault("NUMBER_PARTITION_RULE", "6"));
        numberPartitionNotification = Integer.parseInt(getEnvOrDefault("NUMBER_PARTITION_NOTIFICATION", "6"));

        Map<String, Integer> topicsToCreate = Map.of(
                ALERT_TOPIC, numberPartitionAlert,
                RULE_TOPIC, numberPartitionRule,
                NOTIFICATION_TOPIC, numberPartitionNotification,
                DLQ_TOPIC, numberPartitionAlert);

        AdminClient adminClient = AdminClient.create(Map.of(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer));

        try {
            adminClient.createTopics(topicsToCreate.keySet().stream().map((k) -> {
                return new NewTopic(k, (int) topicsToCreate.get(k), (short) 1);
            }).collect(Collectors.toList())).all().get();
        } catch (Exception e) {
        }

        produceTableRule(bootstrapServer);
    }

    private static void produceTableRule(String bootstrapServer){

        Map<String, ?> mapAvro = Map.of(
                KafkaAvroSerializerConfig.AUTO_REGISTER_SCHEMAS, true,
                KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");

        SpecificAvroSerializer<Rule> avroSerializer = new SpecificAvroSerializer<>();
        avroSerializer.configure(mapAvro, false);

        Map<String, String> map = Map.of(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        Properties properties = new Properties();
        properties.putAll(map);

        KafkaProducer<Integer, Rule> producer = new KafkaProducer<Integer, Rule>(
                properties,
                new IntegerSerializer(),
                avroSerializer);

        Map<Integer, Rule> rules = new HashMap<>();

        rules.put(1, new Rule(1, "Rule 1 - Description", "MAIL"));
        rules.put(2, new Rule(2, "Rule 2 - Description", "SMS"));
        rules.put(3, new Rule(3, "Rule 3 - Description", "PUSH"));
        rules.put(4, new Rule(4, "Rule 4 - Description", "PAPER"));

        for(Integer i : rules.keySet())
            producer.send(new ProducerRecord<>(RULE_TOPIC, i, rules.get(i)));

        producer.flush();
        producer.close(Duration.ofMinutes(1));
    }

    private static String getEnvOrDefault(String key, String defaultValue){
        String v = System.getenv(key);
        return v != null ? v : defaultValue;
    }

    public KafkaStreams getStreams() {
        return streams;
    }

    public HostInfo getHostInfo() {
        return hostInfo;
    }

    public String getBootstrapServer() {
        return bootstrapServer;
    }

    @PostConstruct
    public void start() throws Exception {

        final HostInfo restEndpoint = new HostInfo(restEndpointHostname, restEndpointPort);

        final Map<String, ?> mapSerdes = Map.of(
                KafkaAvroSerializerConfig.AUTO_REGISTER_SCHEMAS, true,
                KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");

        final SpecificAvroSerde<Alert> alertSerde = new SpecificAvroSerde<>();
        alertSerde.configure(mapSerdes, false);

        final SpecificAvroSerde<Rule> ruleSerde = new SpecificAvroSerde<>();
        ruleSerde.configure(mapSerdes, false);

        final SpecificAvroSerde<Notification> notifSerde = new SpecificAvroSerde<>();
        notifSerde.configure(mapSerdes, false);

        final Map<?, ?> map = Map.of(
                StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer,
                StreamsConfig.APPLICATION_ID_CONFIG, applicationId,
                ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest",
                StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.BytesSerde.class,
                StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.BytesSerde.class,
                StreamsConfig.APPLICATION_SERVER_CONFIG, restEndpointHostname + ":" + restEndpointPort);

        final Properties properties = new Properties();
        properties.putAll(map);

        final StreamsBuilder builder = new StreamsBuilder();

        KStream<byte[], Alert> alertStream = builder.stream(ALERT_TOPIC, Consumed.with(Serdes.ByteArray(), alertSerde));

        KTable<Integer, Rule> ruleTable = builder.table(
                RULE_TOPIC,
                Consumed.with(Serdes.Integer(), ruleSerde),
                Materialized.as(RULE_STATE_STORE));

        KStream<Integer, AlertRule> alertRuleStream = alertStream
                .selectKey((k,v) -> v.getRuleId())
                .repartition(
                        Repartitioned
                                .<Integer, Alert>as(REPARTITION_TOPIC)
                                .withKeySerde(Serdes.Integer())
                                .withValueSerde(alertSerde)
                                .withNumberOfPartitions(numberPartitionRule))
                .leftJoin(ruleTable,
                        (alert, rule) -> {
                            AlertRule alertRule = new AlertRule();
                            alertRule.setAlert(alert);
                            alertRule.setRule(rule);
                            return alertRule;
                        });

        Map<String, KStream<Integer, AlertRule>> mapBranchs = alertRuleStream
                .split(Named.as("alert-rule"))
                .branch((k,v) -> v.getRule() != null, Branched.as("-flow"))
                .defaultBranch(Branched.as("-dlq"));

        mapBranchs
                .get("alert-rule-flow")
                .mapValues((ar) -> {
                    Notification notification = new Notification();
                    notification.setDescriptionRule(ar.getRule().getDescription());
                    notification.setDevice(ar.getAlert().getDevice());
                    notification.setDestination(ar.getRule().getDestination());
                    notification.setName(ar.getAlert().getName());
                    notification.setType(ar.getAlert().getType());
                    notification.setRuleId(ar.getAlert().getRuleId());
                    return notification;
                })
                .to(NOTIFICATION_TOPIC, Produced.with(Serdes.Integer(), notifSerde));

        mapBranchs
                .get("alert-rule-dlq")
                .mapValues((ar) -> {
                    AlertDlq alertDlq = new AlertDlq();
                    alertDlq.setDescription("Rule unknown");
                    alertDlq.setDestination("Unknown");
                    alertDlq.setName(ar.getAlert().getName());
                    alertDlq.setDevice(ar.getAlert().getDevice());
                    alertDlq.setType(ar.getAlert().getType());
                    alertDlq.setRuleId(ar.getAlert().getRuleId());
                    return alertDlq;
                })
                .to(DLQ_TOPIC, Produced.with(Serdes.Integer(), CustomSerdes.AlertDlq()));

        streams = new KafkaStreams(builder.build(), properties);
        streams.start();
    }

    @PreDestroy
    public void close(){
        if(streams != null)
            streams.close(Duration.ofSeconds(30));
    }
}
