package mk.finki.ukim.mk.lab.service;


import mk.finki.ukim.mk.lab.model.TicketOrder;
import org.hibernate.annotations.Cascade;

import java.util.Optional;

public interface TicketOrderService{
    TicketOrder placeOrder( String movieTitle, String username, String address, int numberOfTickets);
    Optional<TicketOrder> findById(Long id);
    void save(Long id, Long numTickets, String movieTitle);

    void saveOrder(String movieTitle, String numTickets, String username);

    void deletebyId(Long id);
}