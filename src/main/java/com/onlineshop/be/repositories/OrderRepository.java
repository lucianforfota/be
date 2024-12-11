package com.onlineshop.be.repositories;

import com.onlineshop.be.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {

    List<Order> findAllByUser_IdOrderByCreatedAt(Long userId);
}
