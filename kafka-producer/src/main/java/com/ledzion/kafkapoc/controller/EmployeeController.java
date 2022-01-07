package com.ledzion.kafkapoc.controller;

import com.ledzion.kafkapoc.kafka.KafkaProducer;
import com.ledzion.kafkapoc.model.Employee;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@AllArgsConstructor
public class EmployeeController {

    private final KafkaProducer kafkaProducer;

    @PostMapping
    public ResponseEntity<String> postNewEmployee(Employee employee) {
        log.info("Adding new employee: {}", employee);
        return ResponseEntity.ok("New employee addded.");
    }
}
