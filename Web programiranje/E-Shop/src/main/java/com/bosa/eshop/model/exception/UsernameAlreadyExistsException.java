package com.bosa.eshop.model.exception;

public class UsernameAlreadyExistsException extends RuntimeException{
    public UsernameAlreadyExistsException(String username) {
        super(username + " alreay exists");
    }
}
