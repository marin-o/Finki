package com.bosa.eshop.service;

import com.bosa.eshop.model.User;
import com.bosa.eshop.model.exception.InvalidUserCredentialsException;

public interface AuthService {
    User login( String username, String password) throws InvalidUserCredentialsException;

    User register(String username, String password, String repeatPassword, String name, String surname);
}
