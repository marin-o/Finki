package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();
    Optional<User> findById( Long id );

    User add( String username );
}
