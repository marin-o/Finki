package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.TicketOrder;
import mk.finki.ukim.mk.lab.model.User;

import java.util.List;

public interface UserService {
    List<User> listAll();
    User findUser( String username );
    void addTicketToUser( String username, TicketOrder ticket );
}
