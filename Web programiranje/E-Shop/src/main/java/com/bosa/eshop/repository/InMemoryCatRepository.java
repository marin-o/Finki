package com.bosa.eshop.repository;

import com.bosa.eshop.bootstrap.DataHolder;
import com.bosa.eshop.model.Category;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class InMemoryCatRepository {
    public List<Category> findAll(){
        return DataHolder.categories;
    }
    public Category save(Category c){
        if (c==null || c.getName().isEmpty() || c.getDesc().isEmpty())
            return null;
        DataHolder.categories.removeIf(r->r.getName().equals(c.getName()));
        DataHolder.categories.add(c);
        return c;
    }

    public Optional<Category> findByName(String name){
        return DataHolder.categories.stream().filter(r->r.getName().equals(name))
                .findFirst();
    }

    public List<Category> search(String text){
        return DataHolder.categories.stream().filter(r->r.getName().contains(text) || r.getDesc().contains(text))
                .collect(Collectors.toList());
    }

    public void Delete(String name){
        if(name == null)
            return;
        DataHolder.categories.removeIf(r->r.getName().equals(name));
    }
}
