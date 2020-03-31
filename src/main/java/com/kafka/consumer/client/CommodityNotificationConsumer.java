package com.kafka.consumer.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.consumer.domain.Commodity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * vbala created on 3/31/2020
 * Inside the package - com.kafka.consumer.client
 **/
@Service
@Slf4j
public class CommodityNotificationConsumer {

    private ObjectMapper objectMapper;

    public CommodityNotificationConsumer(ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "t_commodity", groupId = "cg-dashboard")
    public void consumeCommodityMessage(String commodityMessage) throws JsonProcessingException {
        Commodity commodity = objectMapper.readValue(commodityMessage, Commodity.class);
        log.debug("Commodity for notification is: {}", commodity);

    }
}
