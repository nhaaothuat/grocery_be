package com.example.grocery_be.services.product;

import com.example.grocery_be.dtos.ProductRequest;
import com.example.grocery_be.dtos.ProductResponse;
import jakarta.mail.Multipart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    ProductResponse addProduct(ProductRequest productRequest, List<MultipartFile>imageFiles);


    List<ProductResponse> getAllProducts();


    ProductResponse getProductById(String id);


    ProductResponse updateProduct(String id, ProductRequest productRequest);


    void deleteProduct(String id);


    List<ProductResponse> getProductsByCategory(String categoryId);


    List<ProductResponse> searchProductsByName(String keyword);

}
