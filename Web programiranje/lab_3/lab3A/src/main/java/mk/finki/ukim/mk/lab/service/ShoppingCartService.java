package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.ShoppingCart;
import mk.finki.ukim.mk.lab.model.User;

import java.util.List;

public interface ShoppingCartService {
    List<ShoppingCart> findAll();
    List<ShoppingCart> findAllByUser(User user);
    void save(ShoppingCart cart);
}
