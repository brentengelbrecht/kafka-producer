package com.example.kafkaproducer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimpleModel {
    long id;
    String name;
    String message;
}
