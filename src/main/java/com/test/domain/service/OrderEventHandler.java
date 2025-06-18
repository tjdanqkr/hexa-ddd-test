package com.test.domain.service;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class OrderEventHandler {

    @EventListener
    public void handle(OrderCompletedEvent event) {
        // handle event, e.g., logging
        System.out.println("Order completed: " + event.getOrder().getId());
    }
}
