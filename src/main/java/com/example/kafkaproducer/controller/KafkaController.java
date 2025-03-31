package com.example.kafkaproducer.controller;

import com.example.kafkaproducer.model.SimpleModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Nonnull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/kafka")
public class KafkaController {
    @Value("${app.kafka.topic-name}")
    private String topicName;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public KafkaController(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    @PostMapping
    public ResponseEntity<SimpleModel> postMessage(@RequestBody @Nonnull SimpleModel simpleModel) throws JsonProcessingException {
        kafkaTemplate.send(topicName, objectMapper.writeValueAsString(simpleModel));
        return ResponseEntity.ok().body(simpleModel);
    }
}
