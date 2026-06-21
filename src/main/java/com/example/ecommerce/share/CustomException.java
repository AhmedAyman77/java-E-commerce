package com.example.ecommerce.share;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomException extends RuntimeException {
    private int statusCode;
    private String message;

    public static CustomException resourceNotFound(String message) {
        return CustomException(404, message);
    }
}
