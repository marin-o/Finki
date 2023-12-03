package com.bosa.eshop.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class ProductAlreadyInShoppingCartException extends RuntimeException{
    public ProductAlreadyInShoppingCartException(String username, Long productId) {
        super("Product with id " + productId + " is already in shopping cart for user " + username);
    }
}
