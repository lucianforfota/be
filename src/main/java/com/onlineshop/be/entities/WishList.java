package com.onlineshop.be.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class WishList {

    @Id
    @GeneratedValue
    private Long id;


    @Column
    private String name;

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

    @OneToMany(mappedBy = "wishList")
    private List<WishListItem> wishListItems;

    public WishList(){}

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
