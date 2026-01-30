package com.JavaBackend.archie_backend.repository;

import com.JavaBackend.archie_backend.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    // Find user by email (used for login)
    Optional<User> findByEmail(String email);
    // Check if email already exists (used during signup)
    boolean existsByEmail(String email);
}
