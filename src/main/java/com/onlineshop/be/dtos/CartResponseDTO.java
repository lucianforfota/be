package com.onlineshop.be.dtos;

import java.util.List;

public class CartResponseDTO {

    private List<CartItemResponseDTO> cartItems;
    private double totalPrice;

    public CartResponseDTO(){

    }

    public CartResponseDTO(List<CartItemResponseDTO> cartItems, double totalPrice) {
        this.cartItems = cartItems;
        this.totalPrice = totalPrice;
    }

    public List<CartItemResponseDTO> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemResponseDTO> cartItems) {
        this.cartItems = cartItems;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
