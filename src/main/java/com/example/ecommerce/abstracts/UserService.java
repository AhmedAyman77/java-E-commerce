package com.example.ecommerce.abstracts;

import java.util.UUID;

import com.example.ecommerce.dtos.UpdateUser;
import com.example.ecommerce.models.Users;

public interface UserService {
    Users updateUser(UUID userId, UpdateUser updatedUser);
}
