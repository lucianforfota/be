package com.onlineshop.be.repositories;

import com.onlineshop.be.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}
