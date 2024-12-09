package com.onlineshop.be.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String name;

    @Column
    private Double price;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    @OneToMany(mappedBy = "product")
    private List<WishListItem> wishListItems;

    @OneToMany(mappedBy="product")
    private List<CartItem> cartItems;

    @OneToMany(mappedBy="product")
    private List<OrderItem> orderItems;

    @Column
    private Integer stock;


    public Product(){}

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public List<WishListItem> getWishListItems() {
        return wishListItems;
    }

    public void setWishListItems(List<WishListItem> wishListItems) {
        this.wishListItems = wishListItems;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
