package com.example.grocery_be.dtos;

import com.example.grocery_be.enums.UserRole;
import lombok.Data;

@Data
public class UserRequestDTO {
    private String name;
    private String email;
    private String password;

}
