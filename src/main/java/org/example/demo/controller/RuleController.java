package org.example.demo.controller;

import io.confluent.kafka.serializers.KafkaAvroSerializerConfig;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.example.demo.avro.Rule;
import org.example.demo.bean.RuleBean;
import org.example.demo.service.HostStoreInfo;
import org.example.demo.service.MetadataService;
import org.example.demo.streams.LeftJoinKafkaStreams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.websocket.server.PathParam;

import java.util.Map;
import java.util.Properties;

import static org.apache.kafka.streams.StoreQueryParameters.fromNameAndType;

@RestController
@RequestMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class RuleController {

    private final RestTemplate restTemplate;

    private KafkaProducer<Integer, Rule> ruleKafkaProducer = null;

    @Autowired
    private MetadataService metadataService;
    @Autowired
    private LeftJoinKafkaStreams streams;

    public RuleController() {
        restTemplate = new RestTemplate();
    }

    @PostMapping(consumes = "application/json", produces = "application/json", path = "rules")
    public void rulePost(@RequestBody RuleBean ruleBean){

        if(ruleKafkaProducer == null) {
            Map<String, ?> mapAvro = Map.of(
                    KafkaAvroSerializerConfig.AUTO_REGISTER_SCHEMAS, true,
                    KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");

            SpecificAvroSerializer<Rule> avroSerializer = new SpecificAvroSerializer<>();
            avroSerializer.configure(mapAvro, false);

            Map<String, String> map = Map.of(
                    ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, streams.getBootstrapServer());
            Properties properties = new Properties();
            properties.putAll(map);

            ruleKafkaProducer = new KafkaProducer<>(
                    properties,
                    new IntegerSerializer(),
                    avroSerializer);
        }

        ruleKafkaProducer.send(
                new ProducerRecord<>(
                        LeftJoinKafkaStreams.RULE_TOPIC,
                        ruleBean.getId(),
                        new Rule(ruleBean.getId(), ruleBean.getDescription(), ruleBean.getDestination())));
        ruleKafkaProducer.flush();
    }

    @RequestMapping(value = "rules/{ruleId}", method = RequestMethod.GET, produces = "application/json")
    public RuleBean ruleGet(@PathParam("id") final Integer ruleId) throws Exception {

        final HostStoreInfo host = metadataService.streamsMetadataForStoreAndKey(LeftJoinKafkaStreams.RULE_STATE_STORE, ruleId, new IntegerSerializer());

        if (!thisHost(host)) {
            return fetchRule(host, "/rules/" + ruleId);
        }

        final ReadOnlyKeyValueStore<Integer, Rule> ruleStore =
                streams.getStreams().store(fromNameAndType(LeftJoinKafkaStreams.RULE_STATE_STORE, QueryableStoreTypes.keyValueStore()));
        final Rule rule = ruleStore.get(ruleId);

        if (rule == null) {
            throw new Exception(String.format("Rule with id [%d] was not found", ruleId));
        }

        return new RuleBean(rule.getRuleId(), rule.getDescription(), rule.getDestination());
    }


    private RuleBean fetchRule(final HostStoreInfo host, final String path) {
        String url = "http://" + host.getHost() + ":" + host.getPort() + path;
        ResponseEntity<RuleBean> response =  restTemplate.getForEntity(url, RuleBean.class);
        if(response.getStatusCode().is2xxSuccessful())
            return response.getBody();
        else
            return null;
    }

    private boolean thisHost(final HostStoreInfo host) {
        return host.getHost().equals(streams.getHostInfo().host()) &&
                host.getPort() == streams.getHostInfo().port();
    }

}
