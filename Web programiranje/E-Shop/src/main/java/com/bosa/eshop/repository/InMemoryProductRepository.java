package com.bosa.eshop.repository;

import com.bosa.eshop.bootstrap.DataHolder;
import com.bosa.eshop.model.Category;
import com.bosa.eshop.model.Manufacturer;
import com.bosa.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryProductRepository {
    public List<Product> findAll(){
        return DataHolder.products;
    }

    public Optional<Product> findById( Long id ){
        return DataHolder.products
                .stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    public Optional<Product> findByName( String name ){
        return DataHolder.products
                .stream()
                .filter(p -> p.getName().equals(name))
                .findFirst();
    }

    public Optional<Product> save( String name, Double price, Integer quantity,
                                   Category category, Manufacturer manufacturer ){
        DataHolder.products.removeIf(i->i.getName().equals(name));
        Product p = new Product(name, price, quantity,category,manufacturer);
        DataHolder.products.add(p);
        return Optional.of(p);
    }

    public void deleteById(Long id){
        DataHolder.products.removeIf(i->i.getId().equals(id));
    }
}
