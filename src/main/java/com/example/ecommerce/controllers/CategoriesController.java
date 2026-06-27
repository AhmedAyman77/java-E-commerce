package com.example.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.abstracts.CategoryService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.ecommerce.dtos.CreateCategory;
import com.example.ecommerce.models.Categories;
import com.example.ecommerce.share.GlobalResponse;

import org.springframework.web.bind.annotation.GetMapping;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/categories")
public class CategoriesController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<GlobalResponse<Categories>> createCategory(@Valid @RequestBody CreateCategory category) {
        Categories newCategory = categoryService.createCategory(category);

        return  ResponseEntity.status(201)
        .body(
            new GlobalResponse<Categories>(newCategory)
        );
    }
    

    @GetMapping
    public ResponseEntity<GlobalResponse<List<Categories>>> getAllCategories() {
        List<Categories> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(new GlobalResponse<List<Categories>>(categories));
    }
}
