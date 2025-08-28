package com.example.grocery_be.repositories;

import com.example.grocery_be.enities.User;
import com.example.grocery_be.enums.UserRole;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findFirstByEmail(String email);

    Optional<User> findByRole(UserRole role);
}
