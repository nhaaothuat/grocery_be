package com.example.grocery_be.repositories;

import com.example.grocery_be.enities.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface OrderRepository extends MongoRepository<Order,String> {
    Optional<Order> findByOrderCode(String orderCode);
}
