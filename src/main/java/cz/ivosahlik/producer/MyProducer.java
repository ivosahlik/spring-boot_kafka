package cz.ivosahlik.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.ivosahlik.event.Event;
import cz.ivosahlik.event.MyEvent;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Component
public class MyProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper mapper;

    public MyProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper mapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.mapper = mapper;
    }

    public void sendMessage(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }

    public <T> void sendMessage(String topic,
                                UUID key,
                                MyEvent<T> event) throws JsonProcessingException {

        String payload = mapper.writeValueAsString(event);

        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(
                topic,
                key.toString(),
                payload
        );

        producerRecord
                .headers()
                .add("trace_id", UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8));

        kafkaTemplate.send(producerRecord);
    }

    public <T> void sendMessage(String topic,
                            String key,
                            Event<T> event) throws JsonProcessingException {

        String payload = mapper.writeValueAsString(event);

        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(
                topic,
                key,
                payload
        );

        producerRecord
                .headers()
                .add("trace_id", UUID.randomUUID().toString().getBytes(StandardCharsets.UTF_8));

        kafkaTemplate.send(producerRecord);
    }

}
