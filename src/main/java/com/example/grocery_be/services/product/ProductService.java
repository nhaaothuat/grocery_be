package com.example.grocery_be.services.product;

import com.example.grocery_be.dtos.ProductRequest;
import com.example.grocery_be.dtos.ProductResponse;
import jakarta.mail.Multipart;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    ProductResponse addProduct(ProductRequest productRequest, List<MultipartFile>imageFiles);

    // Lấy danh sách tất cả sản phẩm
    List<ProductResponse> getAllProducts();

    // Lấy sản phẩm theo id
    ProductResponse getProductById(String id);

    // Cập nhật sản phẩm
    ProductResponse updateProduct(String id, ProductRequest productRequest);

    // Xóa sản phẩm
    void deleteProduct(String id);

    // Lấy sản phẩm theo category
    List<ProductResponse> getProductsByCategory(String categoryId);

    // Tìm kiếm sản phẩm theo tên
    List<ProductResponse> searchProductsByName(String keyword);

}
