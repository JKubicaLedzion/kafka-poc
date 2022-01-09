package com.ledzion.kafkapoc.kafka;

import com.ledzion.kafkapoc.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaConsumer {

    @KafkaListener(
            topicPartitions = @TopicPartition(
                    topic="${kafka.topicName}",
                    partitions = {"0"}),
            groupId = "${kafka.groupId}")
    public void listenPartition0(@Payload Employee employee,
                                 @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                                 @Header(KafkaHeaders.OFFSET) int offset) {
        log.info("Received message=[{}}] with offset=[{}}] to partition=[{}]", employee, offset, partition);
    }

    @KafkaListener(
            topicPartitions = @TopicPartition(
                    topic="${kafka.topicName}",
                    partitions = {"1"}),
            groupId = "${kafka.groupId}")
    public void listenPartition1(@Payload Employee employee,
                                 @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                                 @Header(KafkaHeaders.OFFSET) int offset) {
        log.info("Received message=[{}}] with offset=[{}}] to partition=[{}]", employee, offset, partition);
    }
}
