package com.example.grocery_be.controllers;

import com.example.grocery_be.dtos.AddToCartRequest;
import com.example.grocery_be.dtos.UserCartDTO;
import com.example.grocery_be.enities.User;
import com.example.grocery_be.services.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
public class CartController {

    private final CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<UserCartDTO> addToCart(
            @AuthenticationPrincipal User user,
            @RequestBody AddToCartRequest request
    ) {
        UserCartDTO updatedUser = cartService.addToCart(user.getId(), request);
        return ResponseEntity.ok(updatedUser);
    }
}
