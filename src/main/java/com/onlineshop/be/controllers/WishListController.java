package com.onlineshop.be.controllers;

import com.onlineshop.be.dtos.WishListItemRequestDTO;
import com.onlineshop.be.dtos.WishListRequestDTO;
import com.onlineshop.be.entities.WishList;
import com.onlineshop.be.entities.WishListItem;
import com.onlineshop.be.services.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wishlist")
public class WishListController {


    private WishListService wishListService;

    @Autowired
    public WishListController(WishListService wishListService) {
        this.wishListService = wishListService;
    }

    @PostMapping
    public ResponseEntity<WishList> createWishlist(@RequestBody WishListRequestDTO wishListRequestDTO){
        WishList wishList = wishListService.addWishListToUser(wishListRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(wishList);
    }

    @PostMapping("/item")
    public ResponseEntity<WishListItem> createWishListItem(@RequestBody  WishListItemRequestDTO wishListItemRequestDTO){
        WishListItem wishlistItem = wishListService.addItemToWishlist(wishListItemRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(wishlistItem);
    }
}
