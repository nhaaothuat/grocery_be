package com.example.grocery_be.dtos;

import lombok.Data;

@Data
public class AddToCartRequest {
    private String productId;

    private int quantity;
}
