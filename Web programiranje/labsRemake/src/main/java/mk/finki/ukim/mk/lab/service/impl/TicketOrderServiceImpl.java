package mk.finki.ukim.mk.lab.service.impl;

import lombok.AllArgsConstructor;
import mk.finki.ukim.mk.lab.model.TicketOrder;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.repository.TicketOrderRepository;
import mk.finki.ukim.mk.lab.service.TicketOrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TicketOrderServiceImpl implements TicketOrderService {

    private final TicketOrderRepository ticketOrderRepository;
    @Override
    public TicketOrder addNewOrder( String movieTitle, String clientName, String address, Long numberOfTickets, User user ) {
        TicketOrder ticketOrder = new TicketOrder(movieTitle,clientName,address,numberOfTickets, user);
        return ticketOrderRepository.addOrder(ticketOrder);
    }

    @Override
    public List<TicketOrder> findAllByUser( User user ) {
        return ticketOrderRepository.findAllByUser(user);
    }

    @Override
    public Optional<TicketOrder> findById( Long ticketId ) {
        return ticketOrderRepository.findById(ticketId);
    }

    @Override
    public void updateOrder( Long orderId, Long numTickets, String movieTitle ) {
        Optional<TicketOrder> ticketOrderOpt = ticketOrderRepository.findById(orderId);
        if(ticketOrderOpt.isPresent()){
            TicketOrder ticketOrder = ticketOrderOpt.get();
            ticketOrder.setNumberOfTickets(numTickets);
            ticketOrder.setMovieTitle(movieTitle);
            ticketOrderRepository.addOrder(ticketOrder);
        }
    }
}
