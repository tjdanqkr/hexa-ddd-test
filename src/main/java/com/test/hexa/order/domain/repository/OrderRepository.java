package com.test.hexa.order.domain.repository;

import com.test.hexa.order.domain.model.Order;

import java.util.Optional;

public interface OrderRepository {
    Order save(Order order);
    Optional<Order> findById(Long id);
}
