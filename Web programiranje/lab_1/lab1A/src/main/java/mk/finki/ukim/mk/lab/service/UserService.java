package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.User;

import java.util.List;

public interface UserService {
    public List<User> listAll();
    public User findUser(String username);
}
