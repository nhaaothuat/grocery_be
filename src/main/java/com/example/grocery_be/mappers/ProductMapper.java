package com.example.grocery_be.mappers;

import com.example.grocery_be.dtos.ProductRequest;
import com.example.grocery_be.dtos.ProductResponse;
import com.example.grocery_be.enities.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toProductRequest(ProductRequest productRequest);

    ProductResponse toProduct(Product product);

}
