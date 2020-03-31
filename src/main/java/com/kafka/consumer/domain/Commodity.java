package com.kafka.consumer.domain;

import lombok.*;

/**
 * vbala created on 3/31/2020
 * Inside the package - com.kafka.produce.domain
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Commodity {

    private String name;
    private double price;
    private String measurement;
    private long timestamp;

}
