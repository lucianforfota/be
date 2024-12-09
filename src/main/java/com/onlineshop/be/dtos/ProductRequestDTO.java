package com.onlineshop.be.dtos;

public class ProductRequestDTO {

    private String name;
    private Double price;
    private Long categoryId;

    private Integer stock;

    public ProductRequestDTO(String name, Double price, Long categoryId, Integer stock) {
        this.name = name;
        this.price = price;
        this.categoryId = categoryId;
        this.stock = stock;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
