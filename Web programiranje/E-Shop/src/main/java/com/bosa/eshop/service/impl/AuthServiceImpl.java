package com.bosa.eshop.service.impl;

import com.bosa.eshop.model.User;
import com.bosa.eshop.model.exception.InvalidArgumentsException;
import com.bosa.eshop.model.exception.InvalidUserCredentialsException;
import com.bosa.eshop.model.exception.PasswordsDoNotMatchException;
import com.bosa.eshop.model.exception.UsernameAlreadyExistsException;
import com.bosa.eshop.repository.jpa.UserRepository;
import com.bosa.eshop.service.AuthService;
import org.springframework.stereotype.Service;
@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private boolean credentialsInvalid(String username, String password) {
        return username == null || password == null || username.isEmpty() || password.isEmpty();
    }

    @Override
    public User login(String username, String password) {
        if (credentialsInvalid(username, password)) {
            throw new InvalidArgumentsException();
        }

        return userRepository.findByUsernameAndPassword(username, password)
                .orElseThrow(InvalidUserCredentialsException::new);
    }

    @Override
    public User register(String username, String password, String repeatPassword, String name, String surname) {
        if (credentialsInvalid(username, password)) {
            throw new InvalidArgumentsException();
        }

        if (!password.equals(repeatPassword)) {
            throw new PasswordsDoNotMatchException();
        }

        if(this.userRepository.findByUsername(username).isPresent())
            throw new UsernameAlreadyExistsException(username);

        User user = new User(username, password, name, surname);
        return userRepository.save(user);
    }
}
