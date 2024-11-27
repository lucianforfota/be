package com.onlineshop.be.repositories;

import com.onlineshop.be.entities.WishListItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishListItemRepository extends JpaRepository<WishListItem,Long> {
}
