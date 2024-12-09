package com.onlineshop.be.services;

import com.onlineshop.be.dtos.ProductRequestDTO;
import com.onlineshop.be.entities.Category;
import com.onlineshop.be.entities.Product;
import com.onlineshop.be.exceptions.ResourceNotFoundException;
import com.onlineshop.be.repositories.CategoryRepository;
import com.onlineshop.be.repositories.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private ProductRepository productRepository;

    private CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository=categoryRepository;
    }

    public Product createProduct(ProductRequestDTO productRequestDTO){

        Category category = categoryRepository.findById(productRequestDTO.getCategoryId()).orElseThrow(()->new ResourceNotFoundException("category not found"));
        Product newProduct = new Product();
        newProduct.setName(productRequestDTO.getName());
        newProduct.setPrice(productRequestDTO.getPrice());
        newProduct.setStock(productRequestDTO.getStock());
        newProduct.setCategory(category);
        return productRepository.save(newProduct);
    }
}
