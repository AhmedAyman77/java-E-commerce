package com.example.ecommerce.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.abstracts.OrderService;
import com.example.ecommerce.models.OrderItems;
import com.example.ecommerce.models.Orders;
import com.example.ecommerce.share.GlobalResponse;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/{userId}/checkout")
    public ResponseEntity<GlobalResponse<Orders>> checkout(@PathVariable UUID userId) {
        Orders order = orderService.checkout(userId);
        return ResponseEntity.status(201).body(new GlobalResponse<>(order));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<GlobalResponse<List<Orders>>> getOrdersByUser(@PathVariable UUID userId) {
        List<Orders> orders = orderService.getOrdersByUserId(userId);
        return ResponseEntity.ok(new GlobalResponse<>(orders));
    }

    @GetMapping("/{orderId}/items")
    public ResponseEntity<GlobalResponse<List<OrderItems>>> getOrderItems(@PathVariable UUID orderId) {
        List<OrderItems> items = orderService.getOrderItemsByOrderId(orderId);
        return ResponseEntity.ok(new GlobalResponse<>(items));
    }
}