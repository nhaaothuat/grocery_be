package com.example.grocery_be.services.category;


import com.example.grocery_be.dtos.CategoryRequest;
import com.example.grocery_be.dtos.CategoryResponse;
import com.example.grocery_be.enities.Category;
import com.example.grocery_be.mappers.CategoryMapper;
import com.example.grocery_be.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public CategoryResponse addCategory(CategoryRequest categoryRequest) {
        Category category = categoryMapper.toCategoryRequestDTO(categoryRequest);
        Category saved = categoryRepository.save(category);

        return categoryMapper.toCategory(saved);
    }

    @Override
    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toCategory).toList();
    }

    @Override
    public Optional<CategoryResponse> getCategoryById(String id) {
        return categoryRepository.findById(id).map(categoryMapper::toCategory);
    }

    @Override
    public CategoryResponse updateCategory(String id, CategoryRequest categoryRequest) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Category not found with: "+id));

        category.setName(categoryRequest.getName());
        category.setDescription(categoryRequest.getDescription());

        Category updated= categoryRepository.save(category);
        return categoryMapper.toCategory(updated);
    }

    @Override
    public void deleteCategory(String id) {
        categoryRepository.deleteById(id);
    }
}
