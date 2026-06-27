package com.example.ecommerce.abstracts;

import java.util.UUID;

import org.springframework.data.domain.Page;

import com.example.ecommerce.dtos.CreateProduct;
import com.example.ecommerce.dtos.UpdateProduct;
import com.example.ecommerce.models.Products;

public interface ProductService {
    Products createProduct(CreateProduct product);
    Page<Products> getAllProducts(int page, int size);
    Products getProductsById(UUID productId);
    Products updateProduct(UUID productId, UpdateProduct product);
    void deleteProduct(UUID productId);
    void uploadProductImage(UUID productId);
}
