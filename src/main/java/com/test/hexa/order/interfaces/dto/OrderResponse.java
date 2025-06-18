package com.test.hexa.order.interfaces.dto;

import com.test.hexa.order.domain.model.Order;
import com.test.hexa.order.domain.model.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private Long id;
    private String product;
    private int quantity;
    private BigDecimal price;
    private OrderStatus status;
    private String createdAt;

    public static OrderResponse from(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getProduct(),
                order.getQuantity(),
                order.getPrice(),
                order.getStatus(),
                order.getCreatedAt() != null ? order.getCreatedAt().toString() : null
        );
    }
}
