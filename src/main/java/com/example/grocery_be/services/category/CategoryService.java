package com.example.grocery_be.services.category;

import com.example.grocery_be.dtos.CategoryRequest;
import com.example.grocery_be.dtos.CategoryResponse;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    CategoryResponse addCategory(CategoryRequest categoryRequest);

    List<CategoryResponse> getAllCategories();

    Optional<CategoryResponse> getCategoryById(String id);

    CategoryResponse updateCategory(String id, CategoryRequest categoryRequest);

    void deleteCategory(String id);
}
