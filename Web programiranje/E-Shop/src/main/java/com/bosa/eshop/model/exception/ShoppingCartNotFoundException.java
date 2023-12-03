package com.bosa.eshop.model.exception;

public class ShoppingCartNotFoundException extends RuntimeException{
    public ShoppingCartNotFoundException(Long id) {
        super("Shopping cart with id "+id+"was not found");
    }
}
