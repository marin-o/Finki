package mk.finki.ukim.mk.lab.model;

import lombok.Data;

@Data
public class TicketOrder {

    private Long id;
    private String movieTitle;
    private String clientName;
    private String clientAddress;
    private Long numberOfTickets;
    private User owner;

    public TicketOrder( String movieTitle, String clientName, String clientAddress, Long numberOfTickets, User owner ) {
        this.id = (long)(Math.random()*1000);
        this.movieTitle=movieTitle;
        this.clientName=clientName;
        this.clientAddress=clientAddress;
        this.numberOfTickets=numberOfTickets;
        this.owner = owner;
    }
}
