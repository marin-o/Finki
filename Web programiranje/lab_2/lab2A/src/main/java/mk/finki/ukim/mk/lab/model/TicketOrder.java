package mk.finki.ukim.mk.lab.model;

import lombok.Data;

@Data
public class TicketOrder {
    private Long id;
    private String movieTitle;
    private String clientName;
    private String clientAddress;
    private Long numberOfTickets;

    public TicketOrder( String movieTitle, String clientName, String clientAddress, Long numberOfTickets ) {
        this.id = (long)(Math.random()*1000);
        this.movieTitle=movieTitle;
        this.clientName=clientName;
        this.clientAddress=clientAddress;
        this.numberOfTickets=numberOfTickets;
    }
}
