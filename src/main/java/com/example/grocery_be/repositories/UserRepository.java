package com.example.grocery_be.repositories;

import com.example.grocery_be.enities.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findFirstByEmail(String email);
}
