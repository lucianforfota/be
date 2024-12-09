package com.onlineshop.be.services;

import com.onlineshop.be.dtos.CartRequestDTO;
import com.onlineshop.be.entities.CartItem;
import com.onlineshop.be.entities.Product;
import com.onlineshop.be.entities.User;
import com.onlineshop.be.exceptions.ResourceNotFoundException;
import com.onlineshop.be.repositories.CartItemRepository;
import com.onlineshop.be.repositories.ProductRepository;
import com.onlineshop.be.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {


    private CartItemRepository cartItemRepository;
    private ProductRepository productRepository;
    private UserRepository userRepository;

    @Autowired
    public CartService(CartItemRepository cartItemRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }




    public CartItem addToCart(CartRequestDTO cartRequestDTO){
        Product product = productRepository.findById(cartRequestDTO.getProductId()).orElseThrow(()->new ResourceNotFoundException("category not found"));
        User user = userRepository.findById(cartRequestDTO.getUserId()).orElseThrow(()->new ResourceNotFoundException("category not found"));
        if (cartRequestDTO.getQuantity()>product.getStock()){
            throw new ResourceNotFoundException("out of stock");
        }
        CartItem newCartItem = new CartItem();
        newCartItem.setQuantity(cartRequestDTO.getQuantity());
        newCartItem.setProduct(product);
        newCartItem.setUser(user);
        return cartItemRepository.save(newCartItem);
    }
}
