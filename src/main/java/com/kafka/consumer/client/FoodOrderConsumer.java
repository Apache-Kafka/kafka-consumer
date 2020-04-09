package com.kafka.consumer.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.consumer.domain.FoodOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA
 * User: Balaji Varadharajan
 * Class/Interface/Enum Name: FoodOrderConsumer
 * Inside the package - com.kafka.consumer.client
 * Created Date: 4/8/2020
 * Created Time: 3:41 PM
 **/
@Service
@Slf4j
public class FoodOrderConsumer {

    private ObjectMapper objectMapper = new ObjectMapper();
    private static final int MAX_AMOUNT_ORDER = 7;

    @KafkaListener(topics = "t_food_order", errorHandler = "foodOrderErrorHandler")
    public void consumeFoodOrders(String message) throws JsonProcessingException {
        FoodOrder foodOrder = objectMapper.readValue(message, FoodOrder.class);
        log.info("Consumed food order : {}", foodOrder );
        if(foodOrder.getAmount() > MAX_AMOUNT_ORDER){
            throw new IllegalArgumentException("Food Order is too many");
        }
    }
}
