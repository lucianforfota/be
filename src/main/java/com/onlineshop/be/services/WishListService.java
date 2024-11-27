package com.onlineshop.be.services;

import com.onlineshop.be.dtos.ProductRequestDTO;
import com.onlineshop.be.dtos.WishListItemRequestDTO;
import com.onlineshop.be.dtos.WishListRequestDTO;
import com.onlineshop.be.entities.*;
import com.onlineshop.be.exceptions.ResourceNotFoundException;
import com.onlineshop.be.repositories.ProductRepository;
import com.onlineshop.be.repositories.UserRepository;
import com.onlineshop.be.repositories.WishListItemRepository;
import com.onlineshop.be.repositories.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishListService {

    private UserRepository userRepository;

    private WishListRepository wishListRepository;

    private WishListItemRepository wishListItemRepository;

    private ProductRepository productRepository;
    @Autowired
    public WishListService(ProductRepository productRepository, UserRepository userRepository, WishListRepository wishListRepository, WishListItemRepository wishListItemRepository) {
        this.userRepository = userRepository;
        this.wishListRepository = wishListRepository;
        this.wishListItemRepository=wishListItemRepository;
        this.productRepository=productRepository;
    }

    public WishList addWishListToUser (WishListRequestDTO wishListRequestDTO){
        User user = userRepository.findById(wishListRequestDTO.getUserId()).orElseThrow(()->new ResourceNotFoundException("category not found"));
        WishList newWishlist = new WishList();
        newWishlist.setName(wishListRequestDTO.getName());
        newWishlist.setUser(user);
        return wishListRepository.save(newWishlist);
    }

    public WishListItem addItemToWishlist(WishListItemRequestDTO wishListItemRequestDTO){
        WishList wishList = wishListRepository.findById(wishListItemRequestDTO.getWishlistId()).orElseThrow(()->new ResourceNotFoundException("category not found"));
        Product product = productRepository.findById(wishListItemRequestDTO.getProductId()).orElseThrow(()->new ResourceNotFoundException("category not found"));
        WishListItem newWishListItem = new WishListItem();
        newWishListItem.setDateAdded(wishListItemRequestDTO.getDate());
        newWishListItem.setWishList(wishList);
        newWishListItem.setProduct(product);
        return wishListItemRepository.save(newWishListItem);
    }
}
