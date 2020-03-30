package com.kafka.consumer.client;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * vbala created on 3/29/2020
 * Inside the package - com.kafka.consumer.client
 **/
@Service
@Slf4j
public class KafkaIncrementConsumer {

    /**
     * This method is to read message without key
     * @param message
     */
//    @KafkaListener(topics = "t_increments")
//    public void consumeMessage(String message){
//      log.debug("Message from Topic:" + message);
//    }

    @KafkaListener(topics = "t_increments_mult_partitions", concurrency = "2")
    public void consumeMessageWithKey(ConsumerRecord<String, String> message) throws InterruptedException {
        log.debug("Partition: {}, Key: {}, Message: {}", message.partition(), message.key(), message.value());
        /**
         * Consume messages every second
         * If "concurrency" parameter is not specified the one message from the queue
         * will be consumed every second.
         * If "concurrency" parameter is set to a value say n( e.g. 2, 3, etc), then n messages
         * can be consumed every second
         */
        Thread.sleep(1000);
    }
}
