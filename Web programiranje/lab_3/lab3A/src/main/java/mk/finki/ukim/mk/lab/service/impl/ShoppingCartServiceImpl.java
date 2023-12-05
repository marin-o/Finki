package mk.finki.ukim.mk.lab.service.impl;

import lombok.AllArgsConstructor;
import mk.finki.ukim.mk.lab.model.ShoppingCart;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.repository.jpa.ShoppingCartRepository;
import mk.finki.ukim.mk.lab.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository cartRepository;

    @Override
    public List<ShoppingCart> findAll() {
        return cartRepository.findAll();
    }

    @Override
    public List<ShoppingCart> findAllByUser(User user) {
        return cartRepository.findAllByUser(user);
    }

    @Override
    public void save(ShoppingCart cart) {
        cartRepository.save(cart);
    }
}
