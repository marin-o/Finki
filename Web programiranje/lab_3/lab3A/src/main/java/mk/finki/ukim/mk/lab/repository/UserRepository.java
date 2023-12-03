package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.TicketOrder;
import mk.finki.ukim.mk.lab.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {

    public List<User> listAll() {
        return DataHolder.users;
    }

    public Optional<User> findUser(String username){
        return DataHolder.users.stream().filter(u -> u.getUsername().equals(username)).findFirst();
    }

    public User addUser(String username){
        User u = new User(username);
        DataHolder.users.add(u);
        return u;
    }

    public void addTicketToUser(String username, TicketOrder ticket){
        User user = findUser(username).orElse(null);
        if(user == null) {
            user = addUser(username);
        }
        user.getOrders().add(ticket);
        DataHolder.orders.add(ticket);
    }

    public Optional<User> findById(Long id) {
        return DataHolder.users.stream().filter(u->u.getId().equals(id)).findFirst();
    }
    public Optional<TicketOrder> findOrder(Long id){
        return DataHolder.orders.stream().filter(o->o.getId().equals(id)).findFirst();
    }

    public void save(Long id, Long numTickets, String movieTitle) {
        findOrder(id).get().setNumberOfTickets(numTickets);
        findOrder(id).get().setMovieTitle(movieTitle);
    }
}
