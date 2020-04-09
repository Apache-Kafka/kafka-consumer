package com.kafka.consumer.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by IntelliJ IDEA
 * User: Balaji Varadharajan
 * Class/Interface/Enum Name: CarLocation
 * Inside the package - com.kafka.consumer.domain
 * Created Date: 4/8/2020
 * Created Time: 2:22 PM
 **/
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class CarLocation {

    @JsonProperty("car_id")
    private String carId;
    private long timestamp;
    private int distance;
}

