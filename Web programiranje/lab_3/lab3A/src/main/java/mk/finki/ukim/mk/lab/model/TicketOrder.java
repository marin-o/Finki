package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class TicketOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String username;
    private String movieTitle;
    private Long numberOfTickets;

    public TicketOrder(String username, String movieTitle, Long numberOfTickets ) {
        this.username = username;
        this.movieTitle=movieTitle;
        this.numberOfTickets=numberOfTickets;
    }
}
