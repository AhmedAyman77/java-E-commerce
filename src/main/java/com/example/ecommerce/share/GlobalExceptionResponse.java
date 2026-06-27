package com.example.ecommerce.share;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.example.ecommerce.share.CustomException;
import com.example.ecommerce.share.GlobalResponse;


@ControllerAdvice
public class GlobalExceptionResponse {

    @ExceptionHandler(NoResourceFoundException.class)
    ResponseEntity<GlobalResponse<String>> handleNoResourceFoundException(NoResourceFoundException ex) {
        var errors = List.of(
            new GlobalResponse.ErrorItems(ex.getMessage())
        );

        return ResponseEntity.status(404).body(new GlobalResponse<String>(errors));
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<GlobalResponse<?>> handleCustomResException(CustomException ex) {
        var errors = List.of(
            new GlobalResponse.ErrorItems(ex.getMessage())
        );

        return ResponseEntity.status(ex.getStatusCode()).body(new GlobalResponse<>(errors));
    }

}
