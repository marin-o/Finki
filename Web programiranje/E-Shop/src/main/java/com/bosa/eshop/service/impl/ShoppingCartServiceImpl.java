package com.bosa.eshop.service.impl;

import com.bosa.eshop.model.Product;
import com.bosa.eshop.model.ShoppingCart;
import com.bosa.eshop.model.User;
import com.bosa.eshop.model.enumeration.ShoppingCartStatus;
import com.bosa.eshop.model.exception.ProductAlreadyInShoppingCartException;
import com.bosa.eshop.model.exception.ProductNotFoundException;
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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final InMemoryShoppingCartRepository shoppingCartRepository;
    private final InMemoryUserRepository userRepository;
    private final ProductService productService;

    public ShoppingCartServiceImpl(InMemoryShoppingCartRepository shoppingCartRepository,
                                   InMemoryUserRepository userRepository,
                                   ProductService productService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.productService = productService;
    }

    @Override
    public List<Product> listAllProductsInShoppingCart(Long cartId) {
        Optional<ShoppingCart> shoppingCartOptional = this.shoppingCartRepository.findById(cartId);

        if (shoppingCartOptional.isEmpty()) {
            throw new ShoppingCartNotFoundException(cartId);
        }

        return shoppingCartOptional.get().getProducts();
    }

    @Override
    public ShoppingCart getActiveShoppingCart(String username) {
        return this.shoppingCartRepository
                .findByUsernameAndStatus(username, ShoppingCartStatus.CREATED)
                .orElseGet(() -> {
                    User user = this.userRepository.findByUsername(username)
                            .orElseThrow(() -> new UserNotFoundException(username));
                    ShoppingCart shoppingCart = new ShoppingCart(user);
                    return this.shoppingCartRepository.save(shoppingCart);
                });
    }

    @Override
    public ShoppingCart addProductToShoppingCart(String username, Long productId) {
        Product product = this.productService.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
        ShoppingCart shoppingCart = this.getActiveShoppingCart(username);
        List<Product> productsInShoppingCart = shoppingCart.getProducts().stream()
                .filter(i -> i.getId().equals(productId))
                .collect(Collectors.toList());

        if (productsInShoppingCart.size() > 0) {
            throw new ProductAlreadyInShoppingCartException(username, productId);
        }

        shoppingCart.getProducts().add(product);
        return this.shoppingCartRepository.save(shoppingCart);
    }
}
