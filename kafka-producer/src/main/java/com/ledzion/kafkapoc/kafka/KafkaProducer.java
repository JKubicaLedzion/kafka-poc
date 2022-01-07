package com.ledzion.kafkapoc.kafka;

import com.ledzion.kafkapoc.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
@Slf4j
public class KafkaProducer {

    private final String topicName;

    private final KafkaTemplate<String, Employee> kafkaTemplate;

    @Autowired
    public KafkaProducer(@Value(value = "${kafka.topicName}") String topicName, KafkaTemplate<String, Employee> kafkaTemplate) {
        this.topicName = topicName;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(Employee employee) {
        log.info("Sending message [{}] to Kafka topic: {}", employee, topicName);

        ListenableFuture<SendResult<String, Employee>> future = kafkaTemplate.send(topicName, employee);

        future.addCallback(new ListenableFutureCallback<>() {

            @Override
            public void onSuccess(SendResult<String, Employee> result) {
                RecordMetadata recordMetadata = result.getRecordMetadata();
                log.info("Sent message=[{}}] with offset=[{}}] to partition=[{}]", employee, recordMetadata.offset(),
                        recordMetadata.partition());
            }

            @Override
            public void onFailure(Throwable exception) {
                log.info("Unable to send message=[{}}] due to: {}", employee, exception.getMessage());
            }
        });
    }
}
