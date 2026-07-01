package com.example.ecommerce.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ecommerce.models.CartItems;

@Repository
public interface CartItemsRepository extends JpaRepository<CartItems, UUID> {
    CartItems findByCartIdAndProductId(UUID cartId, UUID productId);
    List<CartItems> findByCartId(UUID cartId);
}
