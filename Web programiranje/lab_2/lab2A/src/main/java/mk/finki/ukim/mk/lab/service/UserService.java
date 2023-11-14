package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.TicketOrder;
import mk.finki.ukim.mk.lab.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> listAll();
    User findUser( String username );
    void addTicketToUser( String username, TicketOrder ticket );
    Optional<User> findById(Long id);
}
