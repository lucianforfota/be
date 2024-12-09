package com.onlineshop.be.repositories;

import com.onlineshop.be.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {

    public List<CartItem> findAllByUser_Id(Long userId);
    public void deleteAllByUser_Id(Long userId);
}
