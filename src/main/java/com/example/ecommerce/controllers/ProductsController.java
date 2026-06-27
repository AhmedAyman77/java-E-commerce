package com.example.ecommerce.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.dtos.CreateProduct;
import com.example.ecommerce.models.Products;
import com.example.ecommerce.share.GlobalResponse;

import jakarta.validation.Valid;

import com.example.ecommerce.abstracts.ProductService;
import com.example.ecommerce.dtos.UpdateProduct;



@RestController
@RequestMapping("/products")
public class ProductsController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<GlobalResponse<Products>> postMethodName(@Valid @RequestBody CreateProduct product) {
        Products newProduct = productService.createProduct(product);
        return ResponseEntity.status(201)
        .body(
            new GlobalResponse<Products>(newProduct)
        );
    }
    
    @GetMapping
    public ResponseEntity<GlobalResponse<List<Products>>> getAllProducts() {
        List<Products> products = productService.getAllProducts();
        
        return ResponseEntity.ok(
            new GlobalResponse<List<Products>>(products)
        );
    }

    @GetMapping("/{productID}")
    public ResponseEntity<GlobalResponse<Products>> getProductsById(@PathVariable UUID productID) {
        Products product = productService.getProductsById(productID);
        return ResponseEntity.ok(
            new GlobalResponse<Products>(product)
        );
    }
    
    @PutMapping("/{productID}")
    public ResponseEntity<GlobalResponse<Products>> updateProduct(@PathVariable UUID productID,
    @RequestBody UpdateProduct product) {
        Products updatedProduct = productService.updateProduct(productID, product);
        
        return ResponseEntity.ok(
            new GlobalResponse<Products>(updatedProduct)
        );
    }

    @DeleteMapping("/{productID}")
    public ResponseEntity<GlobalResponse<String>> deleteProduct(@PathVariable UUID productID) {
        productService.deleteProduct(productID);

        return ResponseEntity.status(204)
        .body(
            new GlobalResponse<String>("Product deleted successfully")
        );
    }
}
