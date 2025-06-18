package com.test.hexa.application.order;

import com.test.hexa.domain.order.Order;
import java.util.Optional;

public interface OrderRepository {
    Order save(Order order);
    Optional<Order> findById(Long id);
}
