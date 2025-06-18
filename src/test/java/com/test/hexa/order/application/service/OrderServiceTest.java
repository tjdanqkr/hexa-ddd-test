package com.test.hexa.order.application.service;

import com.test.hexa.order.domain.model.Order;
import com.test.hexa.order.domain.model.OrderStatus;
import com.test.hexa.order.domain.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class OrderServiceTest {
    private OrderRepository repository;
    private OrderService service;

    @BeforeEach
    void setUp() {
        repository = Mockito.mock(OrderRepository.class);
        service = new OrderService(repository);
    }

    @Test
    void placeOrder_savesOrder() {
        Order order = Order.builder()
                .product("p1")
                .quantity(1)
                .price(BigDecimal.TEN)
                .status(OrderStatus.NEW)
                .createdAt(Instant.now())
                .build();
        when(repository.save(any(Order.class))).thenReturn(order);

        Order saved = service.placeOrder(order);

        verify(repository).save(order);
        assertThat(saved).isEqualTo(order);
    }

    @Test
    void updateStatus_updatesAndSavesOrder() {
        Order existing = Order.builder()
                .id(1L)
                .product("p1")
                .quantity(1)
                .price(BigDecimal.TEN)
                .status(OrderStatus.NEW)
                .createdAt(Instant.now())
                .build();
        when(repository.findById(1L)).thenReturn(Optional.of(existing));
        when(repository.save(any(Order.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Order updated = service.updateStatus(1L, OrderStatus.SHIPPED);

        ArgumentCaptor<Order> captor = ArgumentCaptor.forClass(Order.class);
        verify(repository).save(captor.capture());
        assertThat(captor.getValue().getStatus()).isEqualTo(OrderStatus.SHIPPED);
        assertThat(updated.getStatus()).isEqualTo(OrderStatus.SHIPPED);
    }
}
