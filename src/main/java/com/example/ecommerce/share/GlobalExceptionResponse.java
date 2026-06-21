package com.example.ecommerce.share;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import com.example.ecommerce.share.CustomResponseException;


@ControllerAdvice
public class GlobalExceptionResponse {

    @ExceptionHandler(NoResourceFoundException.class)
    ResponseEntity<GlobalResponse<String>> handleNoResourceFoundException(NoResourceFoundException ex) {
        var errors = List.of(
            new GlobalResponse.ErrorItems(ex.getMessage())
        );

        return ResponseEntity.status(404).body(new GlobalResponse<String>(errors));
    }

    @ExceptionHandler(CustomResponseException.class)
    public ResponseEntity<GlobalResponse<?>> handleCustomResException(CustomResponseException ex) {
        var errors = List.of(new GlobalResponse.ErrorItem(ex.getMessage()));
        return ResponseEntity.status(ex.getCode()).body(new GlobalResponse<String>(errors));
    }

}
