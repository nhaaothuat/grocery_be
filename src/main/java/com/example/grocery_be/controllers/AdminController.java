package com.example.grocery_be.controllers;

import com.example.grocery_be.dtos.ProductRequest;
import com.example.grocery_be.dtos.ProductResponse;
import com.example.grocery_be.services.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/product")
public class AdminController {

    private final ProductService productService;

    @PostMapping("/add-product")
    public ResponseEntity<ProductResponse> addCProduct(@ModelAttribute ProductRequest productRequest, @RequestParam("images")List<MultipartFile> imgFiles){
       ProductResponse productResponse = productService.addProduct(productRequest,imgFiles);
        return ResponseEntity.ok(productResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id){
        productService.deleteProduct(id);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/all")
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ProductResponse getProductById(@PathVariable String id) {
        return productService.getProductById(id);
    }

    @PutMapping("/{id}")
    public ProductResponse updateProduct(
            @PathVariable String id,
            @RequestBody ProductRequest productRequest) {
        return productService.updateProduct(id, productRequest);
    }


}
