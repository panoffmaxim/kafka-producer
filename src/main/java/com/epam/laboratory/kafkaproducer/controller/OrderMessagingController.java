package com.epam.laboratory.kafkaproducer.controller;

import com.epam.laboratory.kafkaproducer.service.OrderMessagingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/producer/orders")
public class OrderMessagingController {
    private final OrderMessagingService orderMessagingService;

    @PostMapping
    public ResponseEntity<String> sendOrderMessage(@RequestBody String requestMessage) {
        log.info("Send orderId to kafka");
        orderMessagingService.sendOrderMessage(requestMessage);

        return ResponseEntity.ok("Sent");
    }
}
