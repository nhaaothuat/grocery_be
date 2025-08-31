package com.example.grocery_be.services.cart;

import com.example.grocery_be.dtos.AddToCartRequest;
import com.example.grocery_be.dtos.UserCartDTO;
import com.example.grocery_be.enities.CartItem;
import com.example.grocery_be.enities.Product;
import com.example.grocery_be.enities.User;
import com.example.grocery_be.mappers.CartMapper;
import com.example.grocery_be.mappers.UserMapper;
import com.example.grocery_be.repositories.ProductRepository;
import com.example.grocery_be.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService{

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CartMapper cartItemMapper;
    private final UserMapper userMapper;

    @Override
    public UserCartDTO addToCart(String userId, AddToCartRequest request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + userId));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found: " + request.getProductId()));

        CartItem existingItem = user.getCart().stream()
                .filter(item -> item.getProductId().equals(product.getId()))
                .findFirst()
                .orElse(null);

        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + request.getQuantity());
            existingItem.setTotalPrice(existingItem.getQuantity() * product.getPrice());
        } else {
            CartItem newItem = cartItemMapper.toCartItem(request);
            newItem.setTotalPrice(request.getQuantity() * product.getPrice());
            user.getCart().add(newItem);
        }

        return userMapper.toCart(userRepository.save(user));
    }
}
