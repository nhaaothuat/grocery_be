package com.example.grocery_be.dtos;

import lombok.Data;

@Data
public class PaymentRequest {
    private Long totalPrice;
    private String description;
    private String returnUrl;
    private String cancelUrl;
}
