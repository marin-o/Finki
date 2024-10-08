package mk.finki.ukim.mk.lab.service.impl;

import lombok.AllArgsConstructor;
import mk.finki.ukim.mk.lab.model.TicketOrder;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.repository.UserRepository;
import mk.finki.ukim.mk.lab.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> listAll() {
        return userRepository.listAll();
    }

    @Override
    public User findUser(String username) {
        return userRepository.findUser(username).orElse(null);
    }

    @Override
    public void addTicketToUser(String username, TicketOrder ticket) {
        userRepository.addTicketToUser(username, ticket);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }


}
