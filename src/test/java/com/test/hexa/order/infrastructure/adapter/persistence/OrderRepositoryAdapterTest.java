package com.test.hexa.order.infrastructure.adapter.persistence;

import com.test.hexa.order.domain.model.Order;
import com.test.hexa.order.domain.model.OrderStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class OrderRepositoryAdapterTest {
    @Autowired
    private OrderJpaRepository orderJpaRepository;

    @Test
    void save_and_find_order() {
        OrderRepositoryAdapter adapter = new OrderRepositoryAdapter(orderJpaRepository);
        Order order = Order.builder()
                .product("p1")
                .quantity(2)
                .price(BigDecimal.TEN)
                .status(OrderStatus.NEW)
                .createdAt(Instant.now())
                .build();

        Order saved = adapter.save(order);
        Order found = adapter.findById(saved.getId()).orElseThrow();

        assertThat(found.getProduct()).isEqualTo("p1");
        assertThat(found.getQuantity()).isEqualTo(2);
    }
}
