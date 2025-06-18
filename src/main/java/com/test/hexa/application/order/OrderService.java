package com.test.hexa.application.order;

import com.test.hexa.domain.order.Order;

public interface OrderService {
    Order createOrder(Order order);
    Order getOrder(Long id);
    void completeOrder(Long id);
}
