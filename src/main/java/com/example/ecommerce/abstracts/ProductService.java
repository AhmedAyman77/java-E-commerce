package com.example.ecommerce.abstracts;

import java.util.List;
import java.util.UUID;

import com.example.ecommerce.dtos.CreateProduct;
import com.example.ecommerce.dtos.UpdateProduct;
import com.example.ecommerce.models.Products;

public interface ProductService {
    Products createProduct(CreateProduct product);
    List<Products> getAllProducts();
    Products getProductsById(UUID productId);
    Products updateProduct(UUID productId, UpdateProduct product);
    void deleteProduct(UUID productId);
    void uploadProductImage(UUID productId);
}
