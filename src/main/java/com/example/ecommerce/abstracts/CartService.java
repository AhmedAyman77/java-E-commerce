package com.example.ecommerce.abstracts;

import java.util.List;
import java.util.UUID;

import com.example.ecommerce.models.CartItems;

public interface CartService {
    public void addProduct(UUID userId, UUID productId);

    public void removeProduct(UUID userId, UUID productId);

    public List<CartItems> getUserCart(UUID userId);
}
