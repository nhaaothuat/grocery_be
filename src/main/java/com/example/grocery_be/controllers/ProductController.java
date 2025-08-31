package com.example.grocery_be.controllers;

import com.example.grocery_be.dtos.CategoryRequest;
import com.example.grocery_be.dtos.CategoryResponse;
import com.example.grocery_be.dtos.ProductRequest;
import com.example.grocery_be.dtos.ProductResponse;
import com.example.grocery_be.services.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/add-product")
    public ResponseEntity<ProductResponse> addCProduct(@ModelAttribute ProductRequest productRequest, @RequestParam("images")List<MultipartFile> imgFiles){
       ProductResponse productResponse = productService.addProduct(productRequest,imgFiles);
        return ResponseEntity.ok(productResponse);
    }
}
