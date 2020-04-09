package com.kafka.consumer.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.consumer.domain.CarLocation;
import com.kafka.consumer.error.handler.GlobalErrorHandler;
import lombok.SneakyThrows;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.boot.autoconfigure.kafka.ConcurrentKafkaListenerContainerFactoryConfigurer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.adapter.RecordFilterStrategy;
import org.springframework.retry.RetryPolicy;
import org.springframework.retry.backoff.BackOffPolicy;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import java.util.Map;

/**
 * Created by IntelliJ IDEA
 * User: Balaji Varadharajan
 * Class/Interface/Enum Name: KafkaConsumerConfig
 * Inside the package - com.kafka.consumer.config
 * Created Date: 4/8/2020
 * Created Time: 6:58 AM
 **/
@Configuration
public class KafkaConsumerConfig {

    private static KafkaProperties kafkaProperties;

    public KafkaConsumerConfig(KafkaProperties kafkaProperties){
        this.kafkaProperties = kafkaProperties;
    }

    @Bean
    public ConsumerFactory<Object, Object> consumerFactory(){
        Map properties = kafkaProperties.buildConsumerProperties();
        properties.put(ConsumerConfig.METADATA_MAX_AGE_CONFIG, "120000");
        return new DefaultKafkaConsumerFactory<>(properties);
    }

    @Bean(name = "farLocationContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<Object, Object> farLocationContainerFactory(
            ConcurrentKafkaListenerContainerFactoryConfigurer configurer) {
        ConcurrentKafkaListenerContainerFactory factory = new ConcurrentKafkaListenerContainerFactory<Object, Object>();
        configurer.configure(factory, consumerFactory());
        factory.setRecordFilterStrategy(new RecordFilterStrategy() {
            ObjectMapper objectMapper = new ObjectMapper();
            @SneakyThrows
            @Override
            public boolean filter(ConsumerRecord consumerRecord) {
                CarLocation carLocation = objectMapper.readValue(consumerRecord.value().toString(), CarLocation.class);
                return carLocation.getDistance() <= 100;
            }
        });

        return factory;
    }

    @Bean(value = "kafkaListenerContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<Object, Object> kafkaListenerContainerFactory(
            ConcurrentKafkaListenerContainerFactoryConfigurer configurer) {
        ConcurrentKafkaListenerContainerFactory factory = new ConcurrentKafkaListenerContainerFactory<Object, Object>();
        configurer.configure(factory, consumerFactory());
        factory.setErrorHandler(new GlobalErrorHandler());
        return factory;
    }

    @Bean(name = "imageRetryContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<Object, Object> imageRetryContainerFactory(
            ConcurrentKafkaListenerContainerFactoryConfigurer configurer) {
        ConcurrentKafkaListenerContainerFactory factory = new ConcurrentKafkaListenerContainerFactory<Object, Object>();
        configurer.configure(factory, consumerFactory());
        factory.setErrorHandler(new GlobalErrorHandler());
        factory.setRetryTemplate(createRetryTemplate());
        return factory;
    }

    private RetryTemplate createRetryTemplate(){
        RetryTemplate retryTemplate = new RetryTemplate();
        RetryPolicy retryPolicy = new SimpleRetryPolicy(3);
        FixedBackOffPolicy backOffPolicy = new FixedBackOffPolicy();
        backOffPolicy.setBackOffPeriod(10_000);
        retryTemplate.setRetryPolicy(retryPolicy);
        retryTemplate.setBackOffPolicy(backOffPolicy);
        return retryTemplate;
    }
}
