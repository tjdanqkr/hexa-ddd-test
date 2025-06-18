package com.test.domain.order;

import com.test.domain.product.Product;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class OrderTest {

    @Test
    void calculateTotal() {
        Product product = new Product("item", 10, 100);
        OrderItem item = new OrderItem(product, 2);
        Order order = new Order();
        order.addItem(item);
        assertEquals(200, order.calculateTotal());
    }
}
