package com.epam.laboratory.kafkaproducer.controller;

import com.epam.laboratory.kafkaproducer.dto.OrderMessagingDto;
import com.epam.laboratory.kafkaproducer.service.OrderMessagingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
            @RequestBody OrderMessagingDto orderMessagingDto,
            @RequestHeader(value = "Accept-Language", defaultValue = "en-US") String acceptLanguage,
            @RequestHeader(value = "Accept-Timezone", defaultValue = "UTC") String acceptTimezone) {
        try {
            log.info("Send orderId to kafka");
            orderMessagingService.sendOrderMessage(orderMessagingDto, acceptLanguage, acceptTimezone);
            return ResponseEntity.ok("Message sent to kafka topic. OrderId: " + orderMessagingDto.getCompletedOrderId());
        } catch (Exception e) {
            log.error("Error receiving message", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
