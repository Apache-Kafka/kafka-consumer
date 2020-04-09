package com.kafka.consumer.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.consumer.domain.CarLocation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.JobKOctets;

/**
 * Created by IntelliJ IDEA
 * User: Balaji Varadharajan
 * Class/Interface/Enum Name: CarLocationConsumer
 * Inside the package - com.kafka.consumer.client
 * Created Date: 4/8/2020
 * Created Time: 2:23 PM
 **/
@Service
@Slf4j
public class CarLocationConsumer {

    private ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "t_location", groupId = "cg-all-location")
    public void listenAllMessages(String message) throws JsonProcessingException {
        CarLocation carLocation = objectMapper.readValue(message, CarLocation.class);
        log.info("Listen All: {} ", carLocation);
    }

    @KafkaListener(topics = "t_location", groupId = "cg-far-location", containerFactory = "farLocationContainerFactory")
    public void listenFarMessages(String message) throws JsonProcessingException {
        CarLocation carLocation = objectMapper.readValue(message, CarLocation.class);
        log.info("Listen Far: {} ", carLocation);
    }
}
