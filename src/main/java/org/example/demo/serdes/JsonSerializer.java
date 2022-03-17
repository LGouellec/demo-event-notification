package org.example.demo.serdes;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.charset.StandardCharsets;
import java.util.Map;

public class JsonSerializer<T> implements Serializer<T> {

    private final ObjectMapper mapper = new ObjectMapper();

    // default constructor needed by Kafka
    public JsonSerializer() {
    }

    @Override
    public void configure(Map<String, ?> props, boolean isKey) {

        // nothing to do
    }

    @Override
    public byte[] serialize(String topic, T data) {
        if (data == null)
            return null;

        try {
            return mapper.writeValueAsBytes(data);
        } catch (Exception e) {
            throw new SerializationException("Error serializing JSON message", e);
        }
    }

    @Override
    public void close() {
        // nothing to do
    }

}