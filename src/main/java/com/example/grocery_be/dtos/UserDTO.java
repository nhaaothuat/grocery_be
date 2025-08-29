package com.example.grocery_be.dtos;

import com.example.grocery_be.enities.CartItem;
import com.example.grocery_be.enums.UserRole;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class UserDTO {

    @Id
    private String id;
    private String name;
    private String email;


    private UserRole role ;
}
