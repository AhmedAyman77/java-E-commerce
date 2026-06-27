package com.example.ecommerce.abstracts;

import com.example.ecommerce.dtos.LoginUser;
import com.example.ecommerce.dtos.SignupUser;
import com.example.ecommerce.models.Users;

public interface AuthService {
    String login(LoginUser loginUser);
    Users signUp(SignupUser signupUser);
}
