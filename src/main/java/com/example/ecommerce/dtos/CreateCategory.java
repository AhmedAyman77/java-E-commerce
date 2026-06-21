package com.example.ecommerce.dtos;

import jakarta.validation.constraints.NotNull;

public record CreateCategory(
    @NotNull(message = "Category name is required")
    String name
) {}
