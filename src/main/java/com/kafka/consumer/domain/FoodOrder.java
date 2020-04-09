package com.kafka.consumer.domain;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by IntelliJ IDEA
 * User: Balaji Varadharajan
 * Class/Interface/Enum Name: FoodOrder
 * Inside the package - com.kafka.consumer.domain
 * Created Date: 4/8/2020
 * Created Time: 3:40 PM
 **/
@Slf4j
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FoodOrder {

    private int amount;
    private String item;
}
