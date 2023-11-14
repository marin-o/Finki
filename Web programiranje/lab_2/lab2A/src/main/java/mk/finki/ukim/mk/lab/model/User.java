package mk.finki.ukim.mk.lab.model;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class User {
    private Long id;
    private String username;
    private List<TicketOrder> orders;

    public User( String username) {
        this.id = (long)(Math.random() * 1000);
        this.username = username;
        orders = new ArrayList<>();
    }
}
