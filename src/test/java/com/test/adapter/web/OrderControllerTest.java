package com.test.adapter.web;

import com.test.domain.order.Order;
import com.test.domain.order.OrderItem;
import com.test.domain.product.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import com.test.hexa.HexaApplication;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = HexaApplication.class)
@AutoConfigureMockMvc
class OrderControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    void createAndGet() throws Exception {
        Product product = new Product("item", 10, 100);
        OrderItem item = new OrderItem(product, 1);
        Order order = new Order();
        order.addItem(item);

        String json = objectMapper.writeValueAsString(order);

        String location = mockMvc.perform(post("/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
            .andExpect(status().isOk())
            .andReturn().getResponse().getContentAsString();

        Order saved = objectMapper.readValue(location, Order.class);

        mockMvc.perform(get("/orders/" + saved.getId()))
            .andExpect(status().isOk());
    }
}
