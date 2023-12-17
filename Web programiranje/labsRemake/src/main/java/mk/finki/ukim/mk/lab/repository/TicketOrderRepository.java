package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.TicketOrder;
import mk.finki.ukim.mk.lab.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class TicketOrderRepository {
    public TicketOrder addOrder( TicketOrder ticketOrder ) {
        DataHolder.ticketOrders.removeIf(t->t.getId().equals(ticketOrder.getId()));
        DataHolder.ticketOrders.add(ticketOrder);
        return ticketOrder;
    }
    public List<TicketOrder> findAllByUser( User user ) {
        return DataHolder.ticketOrders.stream()
                .filter(
                        t->t.getOwner().getId().equals(user.getId())
                )
                .collect(Collectors.toList());
    }

    public Optional<TicketOrder> findById( Long ticketId ) {
        return DataHolder.ticketOrders.stream()
                .filter(t->t.getId().equals(ticketId))
                .findFirst();
    }
}
