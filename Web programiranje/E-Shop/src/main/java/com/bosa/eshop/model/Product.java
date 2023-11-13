package com.bosa.eshop.model;

import lombok.Data;

@Data
public class Product {
    private Long id;
    private String name;
    private Double price;
    private Integer quantity;
    private Category category;
    private Manufacturer manufacturer;

    public Product( String name, Double price, Integer quantity, Category category, Manufacturer manufacturer ) {
        this.id = (long) (Math.random() * 1000);
        this.name=name;
        this.price=price;
        this.quantity=quantity;
        this.category=category;
        this.manufacturer=manufacturer;
    }
}
