package com.test.hexa.order.infrastructure.adapter.persistence;

import com.test.hexa.order.domain.model.Order;
import com.test.hexa.order.domain.model.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String product;
    private int quantity;
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private Instant createdAt;

    static OrderEntity from(Order order) {
        return OrderEntity.builder()
                .id(order.getId())
                .product(order.getProduct())
                .quantity(order.getQuantity())
                .price(order.getPrice())
                .status(order.getStatus())
                .createdAt(order.getCreatedAt())
                .build();
    }

    Order toModel() {
        return Order.builder()
                .id(id)
                .product(product)
                .quantity(quantity)
                .price(price)
                .status(status)
                .createdAt(createdAt)
                .build();
    }
}
