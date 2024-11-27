package com.onlineshop.be.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.onlineshop.be.entities.WishList;

import java.time.LocalDate;

public class WishListItemRequestDTO {


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;

    private Long wishlistId;

    private Long productId;

    public WishListItemRequestDTO(LocalDate date, Long wishlistId, Long productId) {
        this.date = date;
        this.wishlistId = wishlistId;
        this.productId = productId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(Long wishlistId) {
        this.wishlistId = wishlistId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
