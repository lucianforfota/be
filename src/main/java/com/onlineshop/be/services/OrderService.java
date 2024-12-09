package com.onlineshop.be.services;

import com.onlineshop.be.entities.CartItem;
import com.onlineshop.be.entities.Order;
import com.onlineshop.be.entities.OrderItem;
import com.onlineshop.be.entities.User;
import com.onlineshop.be.exceptions.ResourceNotFoundException;
import com.onlineshop.be.repositories.CartItemRepository;
import com.onlineshop.be.repositories.OrderRepository;
import com.onlineshop.be.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private CartItemRepository cartItemRepository;
    private UserRepository userRepository;

    private OrderRepository orderRepository;

    @Autowired
    public OrderService(CartItemRepository cartItemRepository, UserRepository userRepository, OrderRepository orderRepository) {
        this.cartItemRepository = cartItemRepository;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    @Transactional
    public Order addOrder(Long userId){
        //gasesc toate cart itemurile userului cu userId
        //pentru fiecare caritem
           //creez un nou orderitem unde sa copiez cantitatea
           //il leg de produs
           //il leg de order
        //creez un order
           //ii setez data
           //il leg de user
           //ii setez lista de orderItems
        //sterg toate cart item-urile user-ului cu userId
        //salvez orderul in baza de date

        User user = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("category not found"));
        List<CartItem> cartItems = cartItemRepository.findAllByUser_Id(userId);
        Order order = new Order();
        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem cartItem: cartItems){
            OrderItem orderItem = new OrderItem();
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setOrder(order);
            orderItem.setPrice(cartItem.getQuantity()*cartItem.getProduct().getPrice());
            orderItems.add(orderItem);
        }
        order.setCreatedAt(LocalDateTime.now());
        order.setUser(user);
        order.setOrderItems(orderItems);
        cartItemRepository.deleteAllByUser_Id(userId);
        return orderRepository.save(order);
    }

}
