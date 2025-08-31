package com.example.grocery_be.dtos;

import com.example.grocery_be.enities.CartItem;
import com.example.grocery_be.enums.UserRole;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class UserCartDTO {
    private String id;
    private String email;

    private List<CartItem> cart=new ArrayList<>();
    private UserRole role;
}
