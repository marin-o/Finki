package mk.finki.ukim.mk.lab.repository;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.TicketOrder;
import mk.finki.ukim.mk.lab.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {
    public final List<User> users = new ArrayList<>();

    public List<User> listAll() {
        return users;
    }

    public Optional<User> findUser(String username){
        return users.stream().filter(u -> u.getUsername().equals(username)).findFirst();
    }

    public User addUser(String username){
        User u = new User(username);
        users.add(u);
        return u;
    }

    public void addTicketToUser(String username, TicketOrder ticket){
        User user = findUser(username).orElse(null);
        if(user == null) {
            user = addUser(username);
        }
        user.getOrders().add(ticket);
    }
}
