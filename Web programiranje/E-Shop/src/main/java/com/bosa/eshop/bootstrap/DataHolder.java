package com.bosa.eshop.bootstrap;

import com.bosa.eshop.model.Category;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Category> categories = new ArrayList<>();

    @PostConstruct
    public void init(){
        categories.add(new Category("Softver","so"));
        categories.add(new Category("Books","bo"));
    }
}
