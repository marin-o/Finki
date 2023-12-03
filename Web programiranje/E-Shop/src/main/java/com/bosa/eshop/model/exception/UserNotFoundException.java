package com.bosa.eshop.model.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String userId) {
        super("User with id " + userId + "was not found");
    }
}
