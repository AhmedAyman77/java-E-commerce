package com.example.ecommerce.abstracts;

import java.util.List;

import com.example.ecommerce.models.Categories;
import com.example.ecommerce.dtos.CreateCategory;

public interface CategoryService {
    Categories createCategory(CreateCategory category);
    List<Categories> getAllCategories();
}
