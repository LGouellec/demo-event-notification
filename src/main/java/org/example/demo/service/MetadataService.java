package org.example.demo.service;

import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.streams.KeyQueryMetadata;
import org.apache.kafka.streams.StreamsMetadata;
import org.example.demo.streams.LeftJoinKafkaStreams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Looks up StreamsMetadata from KafkaStreams and converts the results
 * into Beans that can be JSON serialized via Jersey.
 */
@Component
public class MetadataService {

    @Autowired
    private LeftJoinKafkaStreams streams;

    public MetadataService() {
    }

    /**
     * Get the metadata for all of the instances of this Kafka Streams application
     * @return List of {@link HostStoreInfo}
     */
    public List<HostStoreInfo> streamsMetadata() {
        // Get metadata for all of the instances of this Kafka Streams application
        final Collection<StreamsMetadata> metadata = streams.getStreams().metadataForAllStreamsClients();
        return mapInstancesToHostStoreInfo(metadata);
    }

    /**
     * Get the metadata for all instances of this Kafka Streams application that currently
     * has the provided store.
     * @param store   The store to locate
     * @return  List of {@link HostStoreInfo}
     */
    public List<HostStoreInfo> streamsMetadataForStore(final  String store) {
        // Get metadata for all of the instances of this Kafka Streams application hosting the store
        final Collection<StreamsMetadata> metadata = streams.getStreams().streamsMetadataForStore(store);
        return mapInstancesToHostStoreInfo(metadata);
    }

    /**
     * Find the metadata for the instance of this Kafka Streams Application that has the given
     * store and would have the given key if it exists.
     * @param store   Store to find
     * @param key     The key to find
     * @return {@link HostStoreInfo}
     */
    public <K> HostStoreInfo streamsMetadataForStoreAndKey(final String store,
                                                           final K key,
                                                           final Serializer<K> serializer) throws Exception {
        // Get metadata for the instances of this Kafka Streams application hosting the store and
        // potentially the value for key
        final KeyQueryMetadata metadata = streams.getStreams().queryMetadataForKey(store, key, serializer);
        if (metadata == null) {
            throw new Exception();
        }

        return new HostStoreInfo(metadata.activeHost().host(),
                metadata.activeHost().port(),
                Collections.singleton(store));
    }

    private List<HostStoreInfo> mapInstancesToHostStoreInfo(
            final Collection<StreamsMetadata> metadatas) {
        return metadatas.stream().map(metadata -> new HostStoreInfo(metadata.host(),
                        metadata.port(),
                        metadata.stateStoreNames()))
                .collect(Collectors.toList());
    }

}