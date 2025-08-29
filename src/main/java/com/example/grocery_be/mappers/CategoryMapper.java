package com.example.grocery_be.mappers;

import com.example.grocery_be.dtos.CategoryRequest;
import com.example.grocery_be.dtos.CategoryResponse;
import com.example.grocery_be.enities.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toCategoryRequestDTO(CategoryRequest categoryRequest);

    CategoryResponse toCategory(Category category);
}
