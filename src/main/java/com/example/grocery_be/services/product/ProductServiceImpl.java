package com.example.grocery_be.services.product;

import com.example.grocery_be.dtos.ProductRequest;
import com.example.grocery_be.dtos.ProductResponse;
import com.example.grocery_be.enities.Product;
import com.example.grocery_be.mappers.ProductMapper;
import com.example.grocery_be.repositories.CategoryRepository;
import com.example.grocery_be.repositories.ProductRepository;
import com.example.grocery_be.services.Cloudinary.CloudinaryService;

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
        return productRepository.findAll().stream()
                .map(productMapper::toProduct).toList();
    }

    @Override
    public ProductResponse getProductById(String id) {
        return productRepository.findById(id).map(productMapper::toProduct)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    @Override
    public ProductResponse updateProduct(String id, ProductRequest productRequest) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

        // 2. Validate category
        if (productRequest.getCategoryId() != null) {
            productRequest.getCategoryId().forEach(catId -> {
                if (!categoryRepository.existsById(catId)) {
                    throw new IllegalArgumentException("Category not found " + catId);
                }
            });
            existingProduct.setCategoryId(productRequest.getCategoryId());
        }

        // 3. Update field (chỉ update nếu có dữ liệu mới)
        if (productRequest.getName() != null) {
            existingProduct.setName(productRequest.getName());
        }
        if (productRequest.getDescription() != null) {
            existingProduct.setDescription(productRequest.getDescription());
        }
        if (productRequest.getPrice() != null) {
            existingProduct.setPrice(productRequest.getPrice());
        }
        if (productRequest.isInStock()) {
            existingProduct.setInStock(true);
        } else {
            existingProduct.setInStock(false);
        }


        // 5. Trả về DTO
        return productMapper.toProduct(productRepository.save(existingProduct));
    }

    @Override
    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductResponse> getProductsByCategory(String categoryId) {
        List<Product> products = productRepository.findByCategoryIdContaining(categoryId);
        return products.stream()
                .map(productMapper::toProduct)
                .toList();
    }

    @Override
    public List<ProductResponse> searchProductsByName(String keyword) {
        List<Product> products = productRepository.findByNameContainingIgnoreCase(keyword);
        return products.stream()
                .map(productMapper::toProduct)
                .toList();
    }
}
