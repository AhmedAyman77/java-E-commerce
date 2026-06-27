package com.example.ecommerce.abstracts;

import org.springframework.security.core.Authentication;

import com.example.ecommerce.dtos.UpdateUser;
import com.example.ecommerce.models.Users;


public interface UserService {
    Users updateUser(Authentication authentication, UpdateUser updatedUser);
    Users getUserByAuth(Authentication authentication);
}
