package com.example.ecommerce.abstracts;

import com.example.ecommerce.dtos.LoginUser;
import com.example.ecommerce.dtos.SignupUser;
import com.example.ecommerce.models.Users;

public interface AuthService {
    String login(LoginUser loginUser);
    Users signUp(SignupUser signupUser);
    void verifyEmail(String token);
    String refreshToken(String refreshToken);
    public void resendVerificationEmail(String email);
}
