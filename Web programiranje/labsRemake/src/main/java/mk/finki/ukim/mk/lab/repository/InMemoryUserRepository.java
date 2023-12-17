package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryUserRepository {
    public List<User> findAll(){
        return DataHolder.users;
    }

    public Optional<User> findById( Long id ) {
        return DataHolder.users.stream().filter(u->u.getId().equals(id)).findFirst();
    }

    public User add( User user ) {
        DataHolder.users.add(user);
        return user;
    }

    public Optional<User> findByUsername( String username ) {
        return DataHolder.users.stream()
                .filter(u->u.getUsername().equals(username))
                .findFirst();
    }
}
