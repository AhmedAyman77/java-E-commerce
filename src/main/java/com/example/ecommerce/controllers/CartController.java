package com.example.ecommerce.controllers;

import java.util.UUID;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.ecommerce.models.CartItems;

import com.example.ecommerce.abstracts.CartService;
import com.example.ecommerce.share.GlobalResponse;

import org.springframework.web.bind.annotation.GetMapping;



@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/{userId}/add/{productId}")
    public ResponseEntity<GlobalResponse<String>> addProductToUserCart(
        @RequestParam UUID userId,
        @RequestParam UUID productId
    ) {
        cartService.addProduct(userId, productId);
        return ResponseEntity.ok(new GlobalResponse<String>("Product added to cart successfully"));
    }
    
    @GetMapping("/{userId}")
    public ResponseEntity<GlobalResponse<List<CartItems>>> getUserCart(@RequestParam UUID userId) {
        List<CartItems> cartItems = cartService.getUserCart(userId);
        return ResponseEntity.ok(new GlobalResponse<List<CartItems>>(cartItems));
    }
    
    @DeleteMapping("/{userId}/remove/{productId}")
    public ResponseEntity<GlobalResponse<String>> removeProductFromUserCart(
        @RequestParam UUID userId,
        @RequestParam UUID productId
    ) {
        cartService.removeProduct(userId, productId);
        return ResponseEntity.ok(new GlobalResponse<String>("Product removed from cart successfully"));
    }
    

}
