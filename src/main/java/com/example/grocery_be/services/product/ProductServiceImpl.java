package com.example.grocery_be.services.product;

import com.example.grocery_be.dtos.ProductRequest;
import com.example.grocery_be.dtos.ProductResponse;
import com.example.grocery_be.enities.Product;
import com.example.grocery_be.mappers.ProductMapper;
import com.example.grocery_be.repositories.CategoryRepository;
import com.example.grocery_be.repositories.ProductRepository;
import com.example.grocery_be.services.Cloudinary.CloudinaryService;
import jakarta.mail.Multipart;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final CloudinaryService cloudinaryService;
    private final ProductMapper productMapper;

    @Override
    public ProductResponse addProduct(ProductRequest productRequest, List<MultipartFile> imageFiles) {
        List<String> imgUrl = cloudinaryService.uploadFiles(imageFiles);

        if (productRequest.getCategoryId() != null) {
            productRequest.getCategoryId().forEach(catId -> {
                if (!categoryRepository.existsById(catId)) {
                    throw new IllegalArgumentException("Category not found " + catId);
                }
            });
        }

        Product product = productMapper.toProductRequest(productRequest);
        product.setImages(imgUrl);


        return productMapper.toProduct(productRepository.save(product));
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return null;
    }

    @Override
    public ProductResponse getProductById(String id) {
        return null;
    }

    @Override
    public ProductResponse updateProduct(String id, ProductRequest productRequest) {
        return null;
    }

    @Override
    public void deleteProduct(String id) {

    }

    @Override
    public List<ProductResponse> getProductsByCategory(String categoryId) {
        return null;
    }

    @Override
    public List<ProductResponse> searchProductsByName(String keyword) {
        return null;
    }
}
