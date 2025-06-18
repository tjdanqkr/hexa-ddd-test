package com.test.hexa.order.interfaces.rest;

import com.test.hexa.order.application.service.OrderService;
import com.test.hexa.order.domain.model.Order;
import com.test.hexa.order.interfaces.dto.OrderRequest;
import com.test.hexa.order.interfaces.dto.OrderResponse;
import com.test.hexa.order.interfaces.dto.UpdateOrderStatusRequest;
import com.test.hexa.order.domain.model.OrderStatus;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> create(@RequestBody @Valid OrderRequest request) {
        Order order = Order.builder()
                .product(request.getProduct())
                .quantity(request.getQuantity())
                .price(request.getPrice())
                .status(OrderStatus.NEW)
                .createdAt(java.time.Instant.now())
                .build();
        Order saved = orderService.placeOrder(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(OrderResponse.from(saved));
    }

    @GetMapping("/{id}")
    public OrderResponse get(@PathVariable Long id) {
        return OrderResponse.from(orderService.getOrder(id));
    }

    @PatchMapping("/{id}/status")
    public OrderResponse updateStatus(@PathVariable Long id,
                                      @RequestBody @Valid UpdateOrderStatusRequest request) {
        return OrderResponse.from(orderService.updateStatus(id, request.getStatus()));
    }
}
