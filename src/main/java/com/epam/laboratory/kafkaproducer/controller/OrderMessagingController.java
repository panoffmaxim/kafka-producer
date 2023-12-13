package com.epam.laboratory.kafkaproducer.controller;

import com.epam.laboratory.kafkaproducer.service.OrderMessagingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/producer/orders")
public class OrderMessagingController {
    private final OrderMessagingService orderMessagingService;

    @PostMapping
    public ResponseEntity<String> sendOrderMessage(
            @RequestBody String completedOrderId,
            @RequestBody String clientId,
            @RequestHeader(value = "Accept-Language", defaultValue = "en-US") String acceptLanguage,
            @RequestHeader(value = "Accept-Timezone", defaultValue = "UTC") String acceptTimezone) {
        log.info("Send orderId to kafka");
        orderMessagingService.sendOrderMessage(completedOrderId, clientId, acceptLanguage, acceptTimezone);

        return ResponseEntity.ok("Sent");
    }
}
