package com.onlineshop.be.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;

import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String username;

    @Column
    private String password;

    @OneToOne(mappedBy="user")
    private WishList wishList;


    @OneToMany(mappedBy="user")
    private List<CartItem> cartItems;

    @OneToMany(mappedBy="user")
    private List<Order> orders;

    public User(){}

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public WishList getWishList() {
        return wishList;
    }

    public void setWishList(WishList wishList) {
        this.wishList = wishList;
    }
}
