package com.bosa.eshop.model.exception;

public class InvalidUserCredentialsException extends RuntimeException{
    public InvalidUserCredentialsException(){
        super("User credentials invalid");
    }
}
