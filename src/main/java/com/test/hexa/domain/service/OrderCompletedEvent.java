package com.test.hexa.domain.service;

import com.test.hexa.domain.order.Order;
import org.springframework.context.ApplicationEvent;

public class OrderCompletedEvent extends ApplicationEvent {
    public OrderCompletedEvent(Order order) {
        super(order);
    }

    public Order getOrder() {
        return (Order) getSource();
    }
}
