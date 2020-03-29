package com.kafka.consumer.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * vbala created on 3/29/2020
 * Inside the package - com.kafka.consumer.client
 **/
@Service
@Slf4j
public class KafkaIncrementConsumer {

    @KafkaListener(topics = "t_increments")
    public void consumeMessage(String message){
      log.debug("Message from Topic:" + message);
    }
}
