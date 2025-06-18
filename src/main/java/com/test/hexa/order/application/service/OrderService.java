package com.test.hexa.order.application.service;

import com.test.hexa.order.domain.model.Order;
import com.test.hexa.order.domain.model.OrderStatus;
import com.test.hexa.order.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;

    public Order placeOrder(Order order) {
        return orderRepository.save(order);
    }

    @Transactional(readOnly = true)
    public Order getOrder(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Order not found: " + id));
    }

    public Order updateStatus(Long id, OrderStatus status) {
        Order existing = getOrder(id);
        Order updated = Order.builder()
                .id(existing.getId())
                .product(existing.getProduct())
                .quantity(existing.getQuantity())
                .price(existing.getPrice())
                .status(status)
                .createdAt(existing.getCreatedAt())
                .build();
        return orderRepository.save(updated);
    }
}
