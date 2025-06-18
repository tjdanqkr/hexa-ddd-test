package com.test.hexa.order.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

import com.test.hexa.order.domain.model.OrderStatus;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Long id;
    private String product;
    private int quantity;
    private BigDecimal price;
    private OrderStatus status;
    private Instant createdAt;
}
