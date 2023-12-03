package com.bosa.eshop.model;

import com.bosa.eshop.model.enumeration.ShoppingCartStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class ShoppingCart {
    private Long id;
    private LocalDateTime dateCreated;
    private User user;
    private List<Product> products;
    private ShoppingCartStatus status;

    public ShoppingCart() {
        this.id = (long) (Math.random()*1000);
    }

    public ShoppingCart(User user) {
        this.id = (long) (Math.random()*1000);
        this.dateCreated = LocalDateTime.now();
        this.user = user;
        this.products = new ArrayList<>();
        this.status = ShoppingCartStatus.CREATED;
    }
}
