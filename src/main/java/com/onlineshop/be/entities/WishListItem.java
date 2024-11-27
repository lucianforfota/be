package com.onlineshop.be.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class WishListItem {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name="wishlist_id")
    private WishList wishList;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

    @Column
    public LocalDate dateAdded;

    public WishListItem(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public WishList getWishList() {
        return wishList;
    }

    public void setWishList(WishList wishList) {
        this.wishList = wishList;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }
}
