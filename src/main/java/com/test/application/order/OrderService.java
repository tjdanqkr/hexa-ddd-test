package com.test.application.order;

import com.test.domain.order.Order;

public interface OrderService {
    Order createOrder(Order order);
    Order getOrder(Long id);
    void completeOrder(Long id);
}
