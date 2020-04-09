package com.kafka.consumer.error.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.springframework.kafka.listener.ConsumerAwareListenerErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA
 * User: Balaji Varadharajan
 * Class/Interface/Enum Name: FoodOrderErrorHandler
 * Inside the package - com.kafka.consumer.error.handler
 * Created Date: 4/8/2020
 * Created Time: 3:45 PM
 **/
@Service(value = "foodOrderErrorHandler")
@Slf4j
public class FoodOrderErrorHandler implements ConsumerAwareListenerErrorHandler {
    @Override
    public Object handleError(Message<?> message, ListenerExecutionFailedException exception, Consumer<?, ?> consumer) {
        log.warn("Food Order error. Pretending to send to elastic search: {}, because : {}", message.getPayload(), exception.getMessage());

        /**
         * Do the below step only if you want this error to also be caught by the global error handler
         */
        if(exception.getCause() instanceof RuntimeException){
            throw exception;
        }
        return null;
    }
}
