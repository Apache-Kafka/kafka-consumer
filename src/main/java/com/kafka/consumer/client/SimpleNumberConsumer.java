package com.kafka.consumer.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.consumer.domain.SimpleNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA
 * User: Balaji Varadharajan
 * Class/Interface/Enum Name: SimpleNumberConsumer
 * Inside the package - com.kafka.consumer.client
 * Created Date: 4/8/2020
 * Created Time: 4:12 PM
 **/
@Service
@Slf4j
public class SimpleNumberConsumer {

    private ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "t_simple_number")
    public void consumeNumber(String message) throws JsonProcessingException {
        SimpleNumber simpleNumber = objectMapper.readValue(message, SimpleNumber.class);
        log.info("Message Consumed: {}", simpleNumber.getNumber());
        if(simpleNumber.getNumber() % 2 != 0){
            throw new IllegalArgumentException("Odd Number");
        }
    }
}
