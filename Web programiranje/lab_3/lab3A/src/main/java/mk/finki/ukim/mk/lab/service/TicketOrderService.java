package mk.finki.ukim.mk.lab.service;


import mk.finki.ukim.mk.lab.model.TicketOrder;

import java.util.Optional;

public interface TicketOrderService{
    TicketOrder placeOrder( String movieTitle, String clientName, String address, int numberOfTickets);
    Optional<TicketOrder> findById(Long id);
    void save(Long id, Long numTickets, String movieTitle);
}