package com.onlineshop.be.repositories;

import com.onlineshop.be.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
