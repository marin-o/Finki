package com.bosa.eshop.repository.impl;

import com.bosa.eshop.bootstrap.DataHolder;
import com.bosa.eshop.model.ShoppingCart;
import com.bosa.eshop.model.User;
import com.bosa.eshop.model.enumeration.ShoppingCartStatus;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class InMemoryShoppingCartRepository {
    public Optional<ShoppingCart> findById(Long id){
        return DataHolder.shoppingCarts.stream().filter(i->i.getId().equals(id)).findFirst();
    }

    public ShoppingCart save(ShoppingCart cart){
        DataHolder.shoppingCarts.removeIf(i->i.getUser().getUsername().equals(cart.getUser().getUsername()));
        DataHolder.shoppingCarts.add(cart);
        return cart;
    }

    public Optional<ShoppingCart> findByUsernameAndStatus(String username, ShoppingCartStatus status){
        return DataHolder.shoppingCarts
                .stream().filter(i->i.getUser().getUsername().equals(username) && i.getStatus().equals(status)).findFirst();
    }
}
