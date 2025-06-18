package com.test.hexa.domain.product;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private int stock;
    private int price;

    public Product(String name, int stock, int price) {
        this.name = name;
        this.stock = stock;
        this.price = price;
    }

    public void decreaseStock(int quantity) {
        if (stock < quantity) {
            throw new IllegalArgumentException("Not enough stock");
        }
        stock -= quantity;
    }

    public void increaseStock(int quantity) {
        stock += quantity;
    }
}
