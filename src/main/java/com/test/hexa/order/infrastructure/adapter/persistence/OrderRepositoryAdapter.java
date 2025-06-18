package com.test.hexa.order.infrastructure.adapter.persistence;

import com.test.hexa.order.domain.model.Order;
import com.test.hexa.order.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryAdapter implements OrderRepository {
    private final OrderJpaRepository orderJpaRepository;

    @Override
    public Order save(Order order) {
        OrderEntity entity = OrderEntity.from(order);
        OrderEntity saved = orderJpaRepository.save(entity);
        return saved.toModel();
    }

    @Override
    public Optional<Order> findById(Long id) {
        return orderJpaRepository.findById(id).map(OrderEntity::toModel);
    }
}
