package com.example.grocery_be.dtos;

import lombok.Data;

@Data
public class CartItemDTO {
    private String productId;
    private int quantity;
    private long totalPrice;
    private String productName;
}
