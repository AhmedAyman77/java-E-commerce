package com.example.ecommerce.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.abstracts.ProductService;
import com.example.ecommerce.dtos.CreateProduct;
import com.example.ecommerce.dtos.UpdateProduct;
import com.example.ecommerce.models.Categories;
import com.example.ecommerce.models.Products;
import com.example.ecommerce.repository.CategoriesRepository;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.share.CustomException;

@Service
public class ProductServiceImp implements ProductService {
    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private CategoriesRepository categoriesRepo;

    @Override
    public Products createProduct(CreateProduct product) {
        Categories category = categoriesRepo.findById(product.categoryId())
        .orElseThrow(() -> CustomException.resourceNotFound("Category not found with id: " + product.categoryId()));

        if(product.quantity() < 0) {
            throw CustomException.badRequest("Product quantity cannot be negative");
        }

        if(product.price().compareTo(BigDecimal.ZERO) < 0) {
            throw CustomException.badRequest("Product price cannot be negative");
        }

        Products newProduct = new Products();
        newProduct.setName(product.name());
        newProduct.setQuantity(product.quantity());
        newProduct.setPrice(product.price());
        newProduct.setCategory(category);

        productRepo.save(newProduct);
        return newProduct;
    }

    @Override
    public List<Products> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Products getProductsById(UUID productId) {
        Products product = productRepo.findById(productId)
        .orElseThrow(() -> CustomException.resourceNotFound("Product not found with id: " + productId));
        
        return product;
    }

    @Override
    public Products updateProduct(UUID productId, UpdateProduct product) {
        Products currProduct = productRepo.findById(productId)
        .orElseThrow(() -> CustomException.resourceNotFound("Product not found with id: " + productId));
        
        if(product.name() != null) {
            currProduct.setName(product.name());
        }
        if(product.quantity() != null) {
            if(product.quantity() < 0) {
                throw CustomException.badRequest("Product quantity cannot be negative");
            }

            currProduct.setQuantity(product.quantity());
        }
        if(product.price() != null) {
            if(product.price().compareTo(BigDecimal.valueOf(0)) < 0) {
                throw CustomException.badRequest("Product price cannot be negative");
            }

            currProduct.setPrice(product.price());
        }
        if(product.category() != null) {
            Categories category = categoriesRepo.findById(product.category())
            .orElseThrow(() -> CustomException.resourceNotFound("Category not found with id: " + product.category()));
            
            currProduct.setCategory(category);
        }

        return productRepo.save(currProduct);
    }

    @Override
    public void deleteProduct(UUID productId) {
        Products product = productRepo.findById(productId)
        .orElseThrow(() -> CustomException.resourceNotFound("Product not found with id: " + productId));
        
        productRepo.delete(product);
    }

    @Override
    public void uploadProductImage(UUID productId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'uploadProductImage'");
    }

}
