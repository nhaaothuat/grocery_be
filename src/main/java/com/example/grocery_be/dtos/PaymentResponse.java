package com.example.grocery_be.dtos;

import lombok.Data;

@Data
public class PaymentResponse {
    private String orderCode;
    private String description;
    private Object data;
}
