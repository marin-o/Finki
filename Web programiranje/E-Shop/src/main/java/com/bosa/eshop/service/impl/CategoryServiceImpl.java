package com.bosa.eshop.service.impl;

import com.bosa.eshop.model.Category;
import com.bosa.eshop.repository.InMemoryCategoryRepository;
import com.bosa.eshop.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final InMemoryCategoryRepository catRepository;

    public CategoryServiceImpl( InMemoryCategoryRepository catRepository){
        this.catRepository = catRepository;
    }
    @Override
    public Category create( String name, String description ) {
        if (name == null
        || name.isEmpty() || description==null || description.isEmpty())
            throw new IllegalArgumentException();
        Category c = new Category(name,description);
        catRepository.save(c);
        return c;
    }

    @Override
    public Category update( String name, String description ) {
        if (name == null
                || name.isEmpty() || description==null || description.isEmpty())
            throw new IllegalArgumentException();
        Category c = new Category(name,description);
        catRepository.save(c);
        return c;
    }

    @Override
    public void delete( String name ) {
        if (name == null
                || name.isEmpty())
            throw new IllegalArgumentException();
        catRepository.Delete(name);
    }

    @Override
    public List<Category> findAll() {
        return catRepository.findAll();
    }

    @Override
    public List<Category> searchCategories( String searchText ) {
        if (searchText == null
                || searchText.isEmpty())
            throw new IllegalArgumentException();
        return catRepository.search(searchText);
    }
}
