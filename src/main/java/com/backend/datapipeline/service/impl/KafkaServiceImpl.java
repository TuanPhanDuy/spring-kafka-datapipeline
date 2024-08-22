package com.backend.datapipeline.service.impl;

import com.backend.datapipeline.property.KafkaProperties;
import com.backend.datapipeline.service.KafkaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaServiceImpl implements KafkaService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaServiceImpl(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    @Override
    public void sendMessage(String topic, String message) {
        kafkaTemplate.send(topic, message);
    }
}
