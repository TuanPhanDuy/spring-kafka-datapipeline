package com.backend.datapipeline.service;

public interface KafkaService {

    void sendMessage(String topic, String message);
}
