package com.kafka.consumer.domain;

import lombok.*;

/**
 * Created by IntelliJ IDEA
 * User: Balaji Varadharajan
 * Class/Interface/Enum Name: SimpleNumber
 * Inside the package - com.kafka.consumer.domain
 * Created Date: 4/8/2020
 * Created Time: 4:12 PM
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class SimpleNumber {

    private int number;
}
