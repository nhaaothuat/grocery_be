package com.example.grocery_be.controllers;

import com.example.grocery_be.dtos.CategoryRequest;
import com.example.grocery_be.dtos.CategoryResponse;
import com.example.grocery_be.services.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

    @RestController
    @RequiredArgsConstructor
    @RequestMapping("/api/admin/category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/add-category")
    public ResponseEntity<CategoryResponse> addCate(@RequestBody CategoryRequest categoryRequest){
        CategoryResponse addCategory  = categoryService.addCategory(categoryRequest);
        if(addCategory==null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(addCategory);
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable String id){
        return categoryService.getCategoryById(id)
                .map(ResponseEntity::ok)                      // nếu có category thì trả về 200 OK
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable String id){
        categoryService.deleteCategory(id);
        return ResponseEntity.ok(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable String id,@RequestBody CategoryRequest categoryRequest){
        CategoryResponse updated = categoryService.updateCategory(id,categoryRequest);
        return ResponseEntity.ok(updated);
    }


}
