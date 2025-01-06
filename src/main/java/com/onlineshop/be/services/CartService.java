package com.onlineshop.be.services;

import com.onlineshop.be.dtos.CartItemResponseDTO;
import com.onlineshop.be.dtos.CartRequestDTO;
import com.onlineshop.be.dtos.CartResponseDTO;
import com.onlineshop.be.entities.CartItem;
import com.onlineshop.be.entities.Product;
import com.onlineshop.be.entities.User;
import com.onlineshop.be.exceptions.ResourceNotFoundException;
import com.onlineshop.be.repositories.CartItemRepository;
import com.onlineshop.be.repositories.ProductRepository;
import com.onlineshop.be.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        //String usernameLoggedIn = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        if (cartRequestDTO.getQuantity()>product.getStock()){
            throw new ResourceNotFoundException("out of stock");
        }
        CartItem newCartItem = new CartItem();
        newCartItem.setQuantity(cartRequestDTO.getQuantity());
        newCartItem.setProduct(product);
        newCartItem.setUser(user);
        return cartItemRepository.save(newCartItem);
    }

    @Transactional
    public CartResponseDTO viewCart(Long userId){
        CartResponseDTO cartResponseDTO = new CartResponseDTO();
        //iau din db cartiteme user-ului
        List<CartItem> cartItems= cartItemRepository.findAllByUser_Id(userId);

        List<CartItemResponseDTO> cartItemResponseDTOS = cartItems.stream()
                .map(cartItem -> mapFromCartItemToCartItemResponseDTO(cartItem))
                .collect(Collectors.toList());

        cartResponseDTO.setCartItems(cartItemResponseDTOS);
        cartResponseDTO.setTotalPrice(computeTotalPrice(cartItems));
        return cartResponseDTO;
    }

    public Double computeTotalPrice(List<CartItem> cartItems){
        Optional<Double> totalPriceOptional = cartItems.stream()
                .map (cartItem -> cartItem.getQuantity()* cartItem.getProduct().getPrice())
                .reduce((sum, number)->sum+number);
               return totalPriceOptional.orElseThrow(()->new ResourceNotFoundException("price could not be computed"));
    }

    public CartItemResponseDTO mapFromCartItemToCartItemResponseDTO(CartItem cartItem) {
        CartItemResponseDTO cartItemResponseDTO = new CartItemResponseDTO();
        cartItemResponseDTO.setProductName(cartItem.getProduct().getName());
        cartItemResponseDTO.setProductId(cartItem.getProduct().getId());
        cartItemResponseDTO.setQuantity(cartItem.getQuantity());
        cartItemResponseDTO.setPrice(cartItem.getProduct().getPrice());
        return cartItemResponseDTO;
    }
}
