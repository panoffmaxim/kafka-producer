package com.epam.laboratory.kafkaproducer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderMessagingService {

    @Value(value = "${message.topic.name}")
    private String topicName;
    @Autowired
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendOrderMessage(String completedOrderId, String clientId, String acceptLanguage, String acceptTimezone) {
        Map<String, Object> headers = new HashMap<>();
        headers.put("acceptLanguage", acceptLanguage);
        headers.put("acceptTimezone", acceptTimezone);

        String messagePayload = String.format("%s|%s", completedOrderId, clientId);

        Message<String> message = MessageBuilder
                .withPayload(messagePayload)
                .setHeader(KafkaHeaders.TOPIC, topicName)
                .copyHeaders(headers)
                .build();
        kafkaTemplate.send(message);
    }
}
