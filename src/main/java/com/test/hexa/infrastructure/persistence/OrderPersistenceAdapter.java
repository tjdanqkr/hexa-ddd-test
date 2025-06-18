package com.test.hexa.infrastructure.persistence;

import com.test.hexa.application.order.OrderRepository;
import com.test.hexa.domain.order.Order;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class OrderPersistenceAdapter implements OrderRepository {

    private final OrderJpaRepository repository;

    public OrderPersistenceAdapter(OrderJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Order save(Order order) {
        return repository.save(order);
    }

    @Override
    public Optional<Order> findById(Long id) {
        return repository.findById(id);
    }
}
