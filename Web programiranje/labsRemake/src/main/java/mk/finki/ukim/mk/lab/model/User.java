package mk.finki.ukim.mk.lab.model;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String username;

    public User( String username ) {
        this.id = (long)(Math.random()*1000);
        this.username=username;
    }
}
