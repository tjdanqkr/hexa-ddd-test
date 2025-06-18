package com.test.hexa.infrastructure.persistence;

import com.test.hexa.domain.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepository extends JpaRepository<Order, Long> {
}
