package com.example.ecommerce.services;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.abstracts.UserService;
import com.example.ecommerce.dtos.UpdateUser;
import com.example.ecommerce.models.Users;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.share.CustomException;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepo;

    @Override
    public Users updateUser(UUID userId, UpdateUser updatedUser) {
        Users user = userRepo.findById(userId).orElseThrow(
            () -> CustomException.resourceNotFound("User not found with id: " + userId)
        );

        if(updatedUser.username() != null){
            user.setUsername(updatedUser.username());
        }

        if(updatedUser.email() != null) {
            user.setEmail(updatedUser.email());
        }

        return userRepo.save(user);
    }
}
