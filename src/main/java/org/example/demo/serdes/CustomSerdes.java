package org.example.demo.serdes;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.example.demo.bean.AlertDlq;

public final class CustomSerdes {

    private CustomSerdes() { }

    public static Serde<AlertDlq> AlertDlq() {
        JsonSerializer<AlertDlq> serializer = new JsonSerializer<>();
        JsonDeserializer<AlertDlq> deserializer = new JsonDeserializer<>(AlertDlq.class);
        return Serdes.serdeFrom(serializer, deserializer);
    }

}