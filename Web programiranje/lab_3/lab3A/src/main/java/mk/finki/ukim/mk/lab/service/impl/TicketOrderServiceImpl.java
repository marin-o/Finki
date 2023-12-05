package mk.finki.ukim.mk.lab.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import mk.finki.ukim.mk.lab.model.ShoppingCart;
import mk.finki.ukim.mk.lab.model.TicketOrder;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.repository.jpa.ShoppingCartRepository;
import mk.finki.ukim.mk.lab.repository.jpa.TicketOrderRepository;
import mk.finki.ukim.mk.lab.repository.jpa.UserRepository;
import mk.finki.ukim.mk.lab.service.TicketOrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TicketOrderServiceImpl implements TicketOrderService {
    UserRepository userRepository;
    ShoppingCartRepository shoppingCartRepository;
    TicketOrderRepository ticketOrderRepository;
    @Override
    public TicketOrder placeOrder( String movieTitle, String username, String address, int numberOfTickets ) {
        if (movieTitle.isEmpty() || username.isEmpty() || address.isEmpty())
            return null;
        return new TicketOrder(username, movieTitle, (long) numberOfTickets, null);
    }

    @Override
    public Optional<TicketOrder> findById(Long ticketOrderId) {
        List<ShoppingCart> carts = shoppingCartRepository.findAll();
        for (ShoppingCart cart : carts)
            for (TicketOrder ticket : cart.getTicketOrders())
                if (ticket.getId().equals(ticketOrderId))
                    return Optional.of(ticket);

        return Optional.empty();
    }

    @Override
    public void save(Long id, Long numTickets, String movieTitle) {
        //userRepository.save(id, numTickets, movieTitle);
    }

    @Override
    @Transactional
    public void saveOrder(String movieTitle, String numTickets, String username) {
        User user = userRepository.findByUsername(username)
                .orElseGet(() -> new User(username, "noname", "nosurname", "123", LocalDate.now()));

        ShoppingCart cart = shoppingCartRepository.findByUser(user);
        if (cart == null) {
            cart = new ShoppingCart(user);
            cart.setUser(user);
        }

        TicketOrder order = new TicketOrder(username, movieTitle, Long.parseLong(numTickets), cart);
        ticketOrderRepository.save(order);
        cart.getTicketOrders().add(order);

        // Save changes to the database
        userRepository.save(user);
        shoppingCartRepository.save(cart);
    }

    @Override
    @Transactional
    public void deletebyId(Long id) {
        ticketOrderRepository.deleteById(id);
    }


}
