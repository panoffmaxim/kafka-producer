package com.epam.laboratory.kafkaproducer.controller;

import com.epam.laboratory.kafkaproducer.model.Order;
import com.epam.laboratory.kafkaproducer.service.OrderMessagingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.OK)
    public Order sendOrder(@RequestBody Order order) {
        log.info("Send order to kafka");
        orderMessagingService.sendOrder(order);
        return order;
    }
}
