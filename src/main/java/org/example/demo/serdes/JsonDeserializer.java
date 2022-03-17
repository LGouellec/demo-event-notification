package org.example.demo.serdes;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.lang.reflect.Type;
import java.util.Map;

public class JsonDeserializer<T> implements Deserializer<T> {

    private final ObjectMapper mapper = new ObjectMapper();

    private Class<T> destinationClass;
    private Type reflectionTypeToken;

    public JsonDeserializer(Class<T> destinationClass) {
        this.destinationClass = destinationClass;
    }

    public JsonDeserializer(Type reflectionTypeToken) {
        this.reflectionTypeToken = reflectionTypeToken;
    }

    @Override
    public void configure(Map<String, ?> props, boolean isKey) {
        // nothing to do
    }

    @Override
    public T deserialize(String topic, byte[] bytes) {
        if (bytes == null)
            return null;

        try {
            Type type = destinationClass != null ? destinationClass : reflectionTypeToken;
            return mapper.readValue(bytes, destinationClass);
        } catch (Exception e) {
            throw new SerializationException("Error deserializing message", e);
        }
    }

    @Override
    public void close() {
        // nothing to do
    }
}