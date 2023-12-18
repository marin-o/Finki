package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class TicketOrder {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String movieTitle;
    private String clientName;
    private String clientAddress;
    private Long numberOfTickets;
    @ManyToOne
    private ShoppingCart cart;

    public TicketOrder( String movieTitle, String clientName, String clientAddress, Long numberOfTickets, User owner ) {
        this.id = (long)(Math.random()*1000);
        this.movieTitle=movieTitle;
        this.clientName=clientName;
        this.clientAddress=clientAddress;
        this.numberOfTickets=numberOfTickets;
    }
}
