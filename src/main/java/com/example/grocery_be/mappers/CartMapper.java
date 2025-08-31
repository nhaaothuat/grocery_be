package com.example.grocery_be.mappers;

import com.example.grocery_be.dtos.AddToCartRequest;
import com.example.grocery_be.enities.CartItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartMapper {

    CartItem toCartItem(AddToCartRequest request);
}
