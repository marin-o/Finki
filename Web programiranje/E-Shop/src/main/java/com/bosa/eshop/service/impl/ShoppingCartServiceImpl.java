package com.bosa.eshop.service.impl;

import com.bosa.eshop.model.Product;
import com.bosa.eshop.model.ShoppingCart;
import com.bosa.eshop.model.User;
import com.bosa.eshop.model.enumeration.ShoppingCartStatus;
import com.bosa.eshop.model.exception.ProductAlreadyInShoppingCartException;
import com.bosa.eshop.model.exception.ShoppingCartNotFoundException;
import com.bosa.eshop.model.exception.UserNotFoundException;
import com.bosa.eshop.repository.InMemoryProductRepository;
import com.bosa.eshop.repository.InMemoryShoppingCartRepository;
import com.bosa.eshop.repository.InMemoryUserRepository;
import com.bosa.eshop.service.ProductService;
import com.bosa.eshop.service.ShoppingCartService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final InMemoryShoppingCartRepository cartRepository;
    private final InMemoryUserRepository userRepository;
    private final InMemoryProductRepository productRepository;
    @Override
    public List<Product> listAllProductsInShoppingCart(Long cartId) {
        if(this.cartRepository.findById(cartId).isEmpty()){
            throw new ShoppingCartNotFoundException(cartId);
        }
        return this.cartRepository.findById(cartId).get().getProducts();
    }

    @Override
    public ShoppingCart getActiveShoppingCart(String username) {
        return this.cartRepository.findByUsernameAndStatus(username, ShoppingCartStatus.CREATED)
                .orElseGet( () -> {
                    User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
                    ShoppingCart cart = new ShoppingCart(user);
                    return this.cartRepository.save(cart);
                });
    }

    @Override
    public ShoppingCart addProductToShoppingCart(String username, Long productId) {
        ShoppingCart cart = this.getActiveShoppingCart(username);
        Product product = productRepository.findById(productId).orElseThrow(() -> new UserNotFoundException(username));

        if(cart.getProducts().stream().filter(i->i.getId().equals(productId)).collect(Collectors.toList()).size() > 0){
            throw new ProductAlreadyInShoppingCartException(username, productId);
        }

        cart.getProducts().add(product);
        return cartRepository.save(cart);
    }
}
