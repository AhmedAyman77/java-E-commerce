package com.example.ecommerce.abstracts;

import java.util.List;
import java.util.UUID;

import com.example.ecommerce.models.OrderItems;
import com.example.ecommerce.models.Orders;

public interface OrderService {
    Orders checkout(UUID userId);
    List<Orders> getOrdersByUserId(UUID userId);
    List<OrderItems> getOrderItemsByOrderId(UUID orderId);
}
