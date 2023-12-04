package com.bosa.eshop.bootstrap;

import com.bosa.eshop.model.*;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Category> categories = new ArrayList<>();
    public static List<User> users = new ArrayList<>();
    public static List<Manufacturer> manufacturers = new ArrayList<>();
    public static List<Product> products = new ArrayList<>();
    public static List<ShoppingCart> shoppingCarts = new ArrayList<>();
//    @PostConstruct
//    public void init(){
//        categories.add(new Category("Softver","so"));
//        Category cat = new Category("Books","bo");
//        categories.add(cat);
//
//        users.add(new User("marino", "mj", "Marino","Jakimoski"));
//        users.add(new User("farbano", "fb", "Farbano","Bosa"));
//
//        Manufacturer manufacturer = new Manufacturer("Microsoft","a b 2");
//        manufacturers.add(manufacturer);
//        manufacturers.add(new Manufacturer("Finki","b c 3"));
//        products.add(new Product("pc1", 4335355.0,4,cat,manufacturer));
//        products.add(new Product("pc2", 4335356.0,4,cat,manufacturer));
//        products.add(new Product("pc3", 4335357.0,4,cat,manufacturer));
//        products.add(new Product("pc4", 4335358.0,4,cat,manufacturer));
//
//    }
}
