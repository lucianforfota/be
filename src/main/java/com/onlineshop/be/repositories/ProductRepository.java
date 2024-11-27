package com.onlineshop.be.repositories;

import com.onlineshop.be.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
