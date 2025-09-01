package com.example.grocery_be.repositories;

import com.example.grocery_be.enities.Order;
import com.example.grocery_be.enities.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product,String> {
    List<Product> findByNameContainingIgnoreCase(String name);

    List<Product> findByCategoryIdContaining(String categoryId);
}
