package com.bosa.eshop.service;

import com.bosa.eshop.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAll();
    Optional<Product> findById(Long id);
    Optional<Product> findByName(String name);
    Optional<Product> save( String name, Double price, Integer quantity, Long categoryId, Long manufacturerId );
    void deleteById( Long id );

}
