package com.kafka.consumer.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.consumer.domain.Commodity;
import com.kafka.consumer.domain.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * vbala created on 3/31/2020
 * Inside the package - com.kafka.consumer.client
 **/
@Service
@Slf4j
public class CommodityDashboardConsumer {

    private ObjectMapper objectMapper;

    public CommodityDashboardConsumer(ObjectMapper objectMapper){
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "t_commodity", groupId = "cg-notification")
    public void consumeCommodityMessage(String commodityMessage) throws JsonProcessingException {
        Commodity commodity = objectMapper.readValue(commodityMessage, Commodity.class);
        log.debug("Commodity for dashboard is: {}", commodity);

    }
}
