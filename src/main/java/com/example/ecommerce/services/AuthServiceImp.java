package com.example.ecommerce.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ecommerce.abstracts.AuthService;
import com.example.ecommerce.config.JwtHelper;
import com.example.ecommerce.dtos.LoginUser;
import com.example.ecommerce.dtos.SignupUser;
import com.example.ecommerce.enums.UserRole;
import com.example.ecommerce.models.Users;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.share.CustomException;

@Service
public class AuthServiceImp implements AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public String login(LoginUser loginUser) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginUser.username(),
                loginUser.password()
            )
        );

        Users user = userRepository.findByUsername(loginUser.username())
                                    .orElseThrow(() -> CustomException.badCredentials());

        Map<String, Object> customClaims = new HashMap<>();
        customClaims.put("userId", user.getId());
        customClaims.put("role", user.getRole());
        
        return jwtHelper.generateToken(customClaims, user);
    }

    @Override
    public Users signUp(SignupUser signupUser) {
        Optional<Users> existingUsername = userRepository.findByUsername(signupUser.username());
        if(existingUsername.isPresent()) {
            throw CustomException.conflict("Username already exists");
        }

        Optional<Users> existingEmail = userRepository.findByEmail(signupUser.email());
        if(existingEmail.isPresent()) {
            throw CustomException.conflict("Email already exists");
        }

        Users newUser = new Users();
        newUser.setUsername(signupUser.username());
        newUser.setEmail(signupUser.email());
        newUser.setPassword(passwordEncoder.encode(signupUser.password()));
        newUser.setRole(UserRole.CUSTOMER);
        
        return userRepository.save(newUser);
    }

}
