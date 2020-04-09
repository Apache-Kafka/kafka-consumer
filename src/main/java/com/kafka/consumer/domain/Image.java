package com.kafka.consumer.domain;

import lombok.*;

/**
 * Created by IntelliJ IDEA
 * User: Balaji Varadharajan
 * Class/Interface/Enum Name: Image
 * Inside the package - com.kafka.consumer.domain
 * Created Date: 4/8/2020
 * Created Time: 5:29 PM
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Image {

    private String name;
    private long size;
    private String type;
}

