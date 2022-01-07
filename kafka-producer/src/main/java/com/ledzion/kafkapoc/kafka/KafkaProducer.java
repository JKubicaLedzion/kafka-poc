package com.ledzion.kafkapoc.kafka;

import com.ledzion.kafkapoc.model.Employee;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaProducer {

    private final String topicName;

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaProducer(@Value(value = "${kafka.topicName}") String topicName,
                         KafkaTemplate<String, String> kafkaTemplate) {
        this.topicName = topicName;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Employee employee) {
        log.info("Sending message {} to Kafka topic: {}", employee, topicName);
        kafkaTemplate.send(topicName, employee.toString());
    }
}
