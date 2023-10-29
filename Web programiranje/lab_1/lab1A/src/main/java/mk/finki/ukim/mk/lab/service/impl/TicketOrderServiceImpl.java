package mk.finki.ukim.mk.lab.service.impl;

import mk.finki.ukim.mk.lab.model.TicketOrder;
import mk.finki.ukim.mk.lab.service.TicketOrderService;
import org.springframework.stereotype.Service;

@Service
public class TicketOrderServiceImpl implements TicketOrderService {
    TicketOrder order = null;
    @Override
    public TicketOrder placeOrder( String movieTitle, String clientName, String address, int numberOfTickets ) {
        if (movieTitle.isEmpty() || clientName.isEmpty() || address.isEmpty())
            return null;
        return order = new TicketOrder(movieTitle,clientName,address, (long) numberOfTickets);
    }
}
