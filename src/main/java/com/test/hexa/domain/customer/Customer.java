package com.test.hexa.domain.customer;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Customer {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private CustomerGrade grade = CustomerGrade.BASIC;

    public Customer(Long id, String name, CustomerGrade grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    public int discount(int price) {
        if (grade == CustomerGrade.VIP) {
            return (int) (price * 0.9);
        }
        return price;
    }
}
