package com.kafka.consumer.error.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.ConsumerAwareErrorHandler;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA
 * User: Balaji Varadharajan
 * Class/Interface/Enum Name: GlobalErrorHandler
 * Inside the package - com.kafka.consumer.error.handler
 * Created Date: 4/8/2020
 * Created Time: 4:18 PM
 **/
@Service
@Slf4j
public class GlobalErrorHandler implements ConsumerAwareErrorHandler {
    @Override
    public void handle(Exception thrownException, ConsumerRecord<?, ?> data, Consumer<?, ?> consumer) {
      log.warn("Global Error Handler for message: {}", data.value().toString());
    }
}
