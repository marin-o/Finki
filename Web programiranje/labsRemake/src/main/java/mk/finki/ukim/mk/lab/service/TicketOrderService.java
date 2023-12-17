package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.TicketOrder;
import mk.finki.ukim.mk.lab.model.User;

import java.util.List;
import java.util.Optional;

public interface TicketOrderService {
    TicketOrder addNewOrder( String movieTitle, String clientName, String address, Long numberOfTickets, User user);
    List<TicketOrder> findAllByUser( User user );

    Optional<TicketOrder> findById( Long ticketId );

    void updateOrder( Long orderId, Long numTickets, String movieTitle );
}
