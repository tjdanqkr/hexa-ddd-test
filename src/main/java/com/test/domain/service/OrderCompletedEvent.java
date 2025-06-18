package com.test.domain.service;

import com.test.domain.order.Order;
import org.springframework.context.ApplicationEvent;

public class OrderCompletedEvent extends ApplicationEvent {
    public OrderCompletedEvent(Order order) {
        super(order);
    }

    public Order getOrder() {
        return (Order) getSource();
    }
}
