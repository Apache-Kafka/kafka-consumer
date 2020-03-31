package com.kafka.consumer.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

/**
 * vbala created on 3/30/2020
 * Inside the package - com.kafka.consumer.domain
 **/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Employee {

    @JsonProperty("employee_id")
    private UUID employeeId;
    private String employeeName;
    @JsonProperty("birth_Date")
    @JsonDeserialize(using = CustomLocalDateDeserializer.class)
    private LocalDate dateOfBirth;
}
