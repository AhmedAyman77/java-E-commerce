package com.example.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.ecommerce.config.DotenvLoader;

@SpringBootApplication
public class EcommerceApplication {
	public static void main(String[] args) {
		DotenvLoader.load();
		SpringApplication.run(EcommerceApplication.class, args);
	}
}
