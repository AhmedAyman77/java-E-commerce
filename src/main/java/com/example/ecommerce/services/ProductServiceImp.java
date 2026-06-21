package com.example.ecommerce.services;

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
            currProduct.setQuantity(product.quantity());
        }
        if(product.price() != null) {
            currProduct.setPrice(product.price());
        }
        if(product.category() != null) {
            currProduct.setCategory(product.category());
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
