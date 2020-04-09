package com.kafka.consumer.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.consumer.domain.Image;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.net.http.HttpConnectTimeoutException;

/**
 * Created by IntelliJ IDEA
 * User: Balaji Varadharajan
 * Class/Interface/Enum Name: ImageConsumer
 * Inside the package - com.kafka.consumer.client
 * Created Date: 4/8/2020
 * Created Time: 5:46 PM
 **/
@Service
@Slf4j
public class ImageConsumer {

    private ObjectMapper objectMapper = new ObjectMapper();

    @KafkaListener(topics = "t_image", containerFactory = "imageRetryContainerFactory")
    public void consumeImage(String message) throws HttpConnectTimeoutException, JsonProcessingException {
        Image image = objectMapper.readValue(message, Image.class);
        if(image.getType().equals("svg")){
            throw  new HttpConnectTimeoutException("Simulate failed API call");
        } else{
            log.info("Processing Image: {}", image);
        }
    }
}
