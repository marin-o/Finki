package com.bosa.eshop.service.impl;

import com.bosa.eshop.bootstrap.DataHolder;
import com.bosa.eshop.model.Category;
import com.bosa.eshop.model.Manufacturer;
import com.bosa.eshop.model.Product;
import com.bosa.eshop.model.exception.CategoryNotFoundException;
import com.bosa.eshop.model.exception.ManufacturerNotFoundException;
import com.bosa.eshop.repository.impl.InMemoryCategoryRepository;
import com.bosa.eshop.repository.impl.InMemoryManufacturerRepository;
import com.bosa.eshop.repository.impl.InMemoryProductRepository;
import com.bosa.eshop.repository.jpa.CategoryRepository;
import com.bosa.eshop.repository.jpa.ManufacturerRepository;
import com.bosa.eshop.repository.jpa.ProductRepository;
import com.bosa.eshop.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ManufacturerRepository manufacturerRepository;
    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Optional<Product> findByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    @Transactional
    public Optional<Product> save( String name, Double price, Integer quantity, Long categoryId, Long manufacturerId ) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()->new CategoryNotFoundException(categoryId));
        Manufacturer manufacturer = manufacturerRepository.findById(manufacturerId)
                .orElseThrow(()->new ManufacturerNotFoundException(manufacturerId));

        productRepository.deleteByName(name);
        return Optional.of(productRepository.save(new Product(name, price, quantity, category, manufacturer)));
    }

    @Override
    public void deleteById( Long id ) {
        this.productRepository.deleteById(id);
    }
}
