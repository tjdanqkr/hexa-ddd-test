package com.test.hexa.application.order;

import com.test.hexa.domain.order.Order;
import com.test.hexa.domain.order.OrderStatus;
import com.test.hexa.domain.service.OrderCompletedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ApplicationEventPublisher publisher;
    public OrderServiceImpl(OrderRepository orderRepository, ApplicationEventPublisher publisher) {
        this.orderRepository = orderRepository;
        this.publisher = publisher;
    }

    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order getOrder(Long id) {
        return orderRepository.findById(id).orElseThrow();
    }

    @Override
    public void completeOrder(Long id) {
        Order order = getOrder(id);
        order.complete();
        orderRepository.save(order);
        publisher.publishEvent(new OrderCompletedEvent(order));
    }
}
