package mk.finki.ukim.mk.lab.service.impl;

import lombok.AllArgsConstructor;
import mk.finki.ukim.mk.lab.model.TicketOrder;
import mk.finki.ukim.mk.lab.repository.UserRepository;
import mk.finki.ukim.mk.lab.service.TicketOrderService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class TicketOrderServiceImpl implements TicketOrderService {
    UserRepository userRepository;
    @Override
    public TicketOrder placeOrder( String movieTitle, String clientName, String address, int numberOfTickets ) {
        if (movieTitle.isEmpty() || clientName.isEmpty() || address.isEmpty())
            return null;
        return new TicketOrder(movieTitle,clientName,address, (long) numberOfTickets);
    }

    @Override
    public Optional<TicketOrder> findById(Long id) {
        return userRepository.findOrder(id);
    }

    @Override
    public void save(Long id, Long numTickets, String movieTitle) {
        userRepository.save(id, numTickets, movieTitle);
    }
}
