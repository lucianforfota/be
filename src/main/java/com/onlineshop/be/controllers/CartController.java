package com.onlineshop.be.controllers;

import com.onlineshop.be.dtos.CartItemResponseDTO;
import com.onlineshop.be.dtos.CartRequestDTO;
import com.onlineshop.be.dtos.CartResponseDTO;
import com.onlineshop.be.dtos.WishListItemRequestDTO;
import com.onlineshop.be.entities.CartItem;
import com.onlineshop.be.entities.WishListItem;
import com.onlineshop.be.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    private CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<CartItem> addProductToCart(@RequestBody CartRequestDTO cartRequestDTO){
        CartItem cartItem = cartService.addToCart(cartRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(cartItem);
    }
    @GetMapping("/{userId}")
    public ResponseEntity<CartResponseDTO> viewUserCart(@PathVariable Long userId){
        CartResponseDTO cartResponseDTO = cartService.viewCart(userId);
        return ResponseEntity.ok(cartResponseDTO);
    }

}
