package com.kafka.consumer.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.consumer.domain.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * vbala created on 3/30/2020
 * Inside the package - com.kafka.consumer.client
 **/
@Service
@Slf4j
public class EmployeeMessageConsumer {

    private ObjectMapper objectMapper;

    public EmployeeMessageConsumer(ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "t_employee")
    public void consumeEmployeeMessage(String employeeMessage) throws JsonProcessingException {
        Employee employee = objectMapper.readValue(employeeMessage, Employee.class);
        log.debug("Employee is: {}", employee);

    }
}
