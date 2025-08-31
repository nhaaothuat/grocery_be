package com.example.grocery_be.services.cart;

import com.example.grocery_be.dtos.AddToCartRequest;
import com.example.grocery_be.dtos.UserCartDTO;
import com.example.grocery_be.enities.User;

public interface CartService {
    UserCartDTO addToCart(String userId, AddToCartRequest request);
}
