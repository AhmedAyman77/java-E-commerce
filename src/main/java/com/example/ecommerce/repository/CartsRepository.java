package com.example.ecommerce.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ecommerce.models.Carts;

@Repository
public interface CartsRepository extends JpaRepository<Carts, UUID> {}
