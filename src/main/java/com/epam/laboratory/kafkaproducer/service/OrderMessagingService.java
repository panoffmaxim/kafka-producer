package com.epam.laboratory.kafkaproducer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderMessagingService {

    @Value(value = "${message.topic.name}")
    private String topicName;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendOrderMessage(String completedOrderId) {
        kafkaTemplate.send(topicName, completedOrderId);
    }
}
