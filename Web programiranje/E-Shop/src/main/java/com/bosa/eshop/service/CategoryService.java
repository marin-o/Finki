package com.bosa.eshop.service;

import com.bosa.eshop.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    Optional<Category> findById(Long id);

    Category create(String name, String description);

    Category update(String name,String description);

    void delete(String name);

    List<Category> findAll();

    List<Category> searchCategories(String searchText);
}
