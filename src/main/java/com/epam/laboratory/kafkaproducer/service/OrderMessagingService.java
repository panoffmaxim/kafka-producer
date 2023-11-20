package com.epam.laboratory.kafkaproducer.service;

import com.epam.laboratory.kafkaproducer.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderMessagingService {

    @Value(value = "${message.topic.name}")
    private String topicName;

    private final KafkaTemplate<String , Order> kafkaTemplate;

    public void sendOrder(Order order) {
        kafkaTemplate.send(topicName, order.getId(), order);
    }
}
