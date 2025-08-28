package com.example.grocery_be.dtos;

import lombok.Data;

@Data
public class UserInRequestDTO {
    private String email;
    private String password;
}
