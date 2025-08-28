package com.example.grocery_be.dtos;

import com.example.grocery_be.enums.UserRole;
import lombok.Data;

@Data
public class UserResponseDTO {
    private String id;
    private String email;
    private String jwt;

    private UserRole role;
}
