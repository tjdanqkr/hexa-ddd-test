package com.test.hexa.order.interfaces.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderRequest {
    @NotBlank
    private String product;

    @Min(1)
    private int quantity;

    @DecimalMin("0.0")
    private BigDecimal price;
}
