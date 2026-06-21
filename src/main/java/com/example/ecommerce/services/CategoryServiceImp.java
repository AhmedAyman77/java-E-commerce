package com.example.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.ecommerce.abstracts.CategoryService;
import com.example.ecommerce.dtos.CreateCategory;
import com.example.ecommerce.models.Categories;
import com.example.ecommerce.repository.CategoriesRepository;

public class CategoryServiceImp implements CategoryService {

    @Autowired
    private CategoriesRepository categoriesRepository;

    @Override
    public Categories createCategory(CreateCategory category) {
        Categories newCategory = new Categories();
        newCategory.setName(category.getName());

        return categoriesRepository.save(newCategory);
    }

    @Override
    public List<Categories> getAllCategories() {
        return categoriesRepository.findAll();
    }
}
