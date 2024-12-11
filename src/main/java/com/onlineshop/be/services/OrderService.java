package com.onlineshop.be.services;

import com.onlineshop.be.entities.*;
import com.onlineshop.be.exceptions.ResourceNotFoundException;
import com.onlineshop.be.repositories.CartItemRepository;
import com.onlineshop.be.repositories.OrderRepository;
import com.onlineshop.be.repositories.ProductRepository;
import com.onlineshop.be.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private CartItemRepository cartItemRepository;
    private UserRepository userRepository;

    private OrderRepository orderRepository;

    private CartService cartService;

    private ProductRepository productRepository;

    @Autowired
    public OrderService(CartItemRepository cartItemRepository, CartService cartService, UserRepository userRepository, OrderRepository orderRepository, ProductRepository productRepository) {
        this.cartItemRepository = cartItemRepository;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.cartService = cartService;
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
        if (cartItems.size()==0){
            throw new ResourceNotFoundException("order cannot be placed because cart is empty");
        }

        Order order = new Order();
        List<OrderItem> orderItems = cartItems.stream()
                .map(cartItem -> mapFromCartItemToOrderItem(cartItem, order))
                .collect(Collectors.toList());

        order.setCreatedAt(LocalDateTime.now());
        order.setUser(user);
        order.setOrderItems(orderItems);
        order.setTotalPrice(cartService.computeTotalPrice(cartItems));
        //updatez stockurile produselor din cos
        for (CartItem cartItem : cartItems){
            Product cartProduct = cartItem.getProduct();
            cartProduct.setStock(cartProduct.getStock()-cartItem.getQuantity());
            productRepository.save(cartProduct);
        }
        cartItemRepository.deleteAllByUser_Id(userId);
        return orderRepository.save(order);
    }


    public OrderItem mapFromCartItemToOrderItem (CartItem cartItem, Order order){
        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(cartItem.getQuantity());
        orderItem.setProduct(cartItem.getProduct());
        orderItem.setOrder(order);
        orderItem.setPrice(cartItem.getQuantity()*cartItem.getProduct().getPrice());
        return orderItem;
    }

    public List<Order> findOrdersByUserId(Long userId){
        return orderRepository.findAllByUser_IdOrderByCreatedAt(userId);
    }

}
