package mk.finki.ukim.mk.lab.web.controller;

import lombok.AllArgsConstructor;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.service.TicketOrderService;
import mk.finki.ukim.mk.lab.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/movies/ticketOrder")
@AllArgsConstructor
public class TicketOrderController {
    private final TicketOrderService ticketOrderService;
    private final UserService userService;

    @PostMapping
    public String getOrderConfirmationPage( @RequestParam String movieTitle,
                                            @RequestParam Long numTickets,
                                            @RequestParam String username,
                                            Model model ){
        User user = userService.add(username);
        model.addAttribute("ticket",
                ticketOrderService.addNewOrder(movieTitle,username,"127.0.0.1",numTickets,user));
        return "orderConfirmation";
    }
}
