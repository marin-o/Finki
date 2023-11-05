package com.bosa.eshop.service.impl;

import com.bosa.eshop.model.User;
import com.bosa.eshop.model.exception.InvalidArgumentsException;
import com.bosa.eshop.model.exception.InvalidUserCredentialsException;
import com.bosa.eshop.model.exception.PasswordsDoNotMatchException;
import com.bosa.eshop.repository.InMemoryUserRepository;
import com.bosa.eshop.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final InMemoryUserRepository userRepository;

    @Override
    public User login( String username, String password ) throws InvalidUserCredentialsException {
        if (username == null || username.isEmpty() || password == null || password.isEmpty())
            throw new InvalidArgumentsException();
        return userRepository.findByUsernameAndPassword(username,password)
                .orElseThrow(InvalidUserCredentialsException::new);
    }

    @Override
    public User register( String username, String password, String repeatPassword, String name, String surname ) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty())
            throw new InvalidArgumentsException();
        if(password.equals(repeatPassword)){
            throw new PasswordsDoNotMatchException();
        }
        User user = new User(username,password,name,surname);
        return userRepository.saveOrUpdate(user);
    }
}
