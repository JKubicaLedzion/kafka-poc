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

    public static final String LOG_MESSAGE_WITH_OFFSET_TO_PARTITION_BY_GROUP_ID =
            "Received message=[{}}] with offset=[{}}] to partition=[{}] by group id=[{}]";

    @KafkaListener(
            topicPartitions = @TopicPartition(
                    topic="${kafka.topicName}",
                    partitions = {"0", "1"}),
            groupId = "${kafka.groupId}")
    public void listenPartition0and1(@Payload Employee employee,
                                     @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                                     @Header(KafkaHeaders.OFFSET) int offset,
                                     @Header(KafkaHeaders.GROUP_ID) String groupId) {
        log.info("listenPartition0and1: " + LOG_MESSAGE_WITH_OFFSET_TO_PARTITION_BY_GROUP_ID, employee, offset,
                partition, groupId);
    }

    @KafkaListener(
            topicPartitions = @TopicPartition(
                    topic="${kafka.topicName}",
                    partitions = {"2", "3"}),
            groupId = "${kafka.groupId}")
    public void listenPartition2and3(@Payload Employee employee,
                                     @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                                     @Header(KafkaHeaders.OFFSET) int offset,
                                     @Header(KafkaHeaders.GROUP_ID) String groupId) {
        log.info("listenPartition2and3: " + LOG_MESSAGE_WITH_OFFSET_TO_PARTITION_BY_GROUP_ID, employee, offset,
                partition, groupId);
    }
}
