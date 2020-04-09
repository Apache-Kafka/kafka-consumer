package com.kafka.consumer.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kafka.consumer.domain.Commodity;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA
 * User: Balaji Varadharajan
 * Class/Interface/Enum Name: RebalanceConsumer
 * Inside the package - com.kafka.consumer.client
 * Created Date: 4/8/2020
 * Created Time: 6:31 AM
 **/
@Service
@Slf4j
public class RebalanceConsumer {

    @KafkaListener(topics = "t_rebalance", concurrency = "3")
    public void consumer(ConsumerRecord<String, String> message) {
        log.debug("Partition : {}, " +
                "Offset : {}" +
                "Message: {}", message.partition(), message.offset(), message.value());
    }
}
