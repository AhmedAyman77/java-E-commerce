package com.example.ecommerce.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ecommerce.models.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, UUID> {
    Optional<Users> findByUsername(String username);
    Optional<Users> findByEmail(String email);
}
