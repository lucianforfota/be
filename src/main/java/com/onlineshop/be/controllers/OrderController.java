package com.onlineshop.be.controllers;


import com.onlineshop.be.entities.CartItem;
import com.onlineshop.be.entities.Order;
import com.onlineshop.be.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Order> addOrder(@PathVariable Long userId){
        Order order = orderService.addOrder(userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }
}
