package mk.finki.ukim.mk.lab.model;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class User {
    private String username;
    private List<TicketOrder> orders;

    public User(String username) {
        this.username = username;
        orders = new ArrayList<>();
    }
}
