package com.ledzion.kafkapoc.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = "${kafka.topicName}", groupId = "${kafka.groupId}")
    public void listen(String message) {
        log.info("Received message: {}, in group HR", message);
    }
}
