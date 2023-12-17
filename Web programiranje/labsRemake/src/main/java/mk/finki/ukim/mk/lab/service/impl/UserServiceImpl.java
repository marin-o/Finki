package mk.finki.ukim.mk.lab.service.impl;

import lombok.AllArgsConstructor;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.repository.InMemoryUserRepository;
import mk.finki.ukim.mk.lab.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final InMemoryUserRepository inMemoryUserRepository;
    @Override
    public List<User> findAll() {
        return inMemoryUserRepository.findAll();
    }

    @Override
    public Optional<User> findById( Long id ) {
        return inMemoryUserRepository.findById(id);
    }

    @Override
    public User add( String username ) {
        User user = inMemoryUserRepository.findByUsername(username).orElse(null);
        if(user==null){
            user = inMemoryUserRepository.add(new User(username));
        }
        return user;
    }
}
