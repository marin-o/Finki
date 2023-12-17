package mk.finki.ukim.mk.lab.web.controller;

import lombok.AllArgsConstructor;
import mk.finki.ukim.mk.lab.model.TicketOrder;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.service.MovieService;
import mk.finki.ukim.mk.lab.service.TicketOrderService;
import mk.finki.ukim.mk.lab.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/movies/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final TicketOrderService ticketOrderService;
    private final MovieService movieService;

    @GetMapping
    public String getOrderConfirmationPage( Model model){
        model.addAttribute("users",userService.findAll());
        return "userHistory";
    }

    @PostMapping
    public String getTicketsFromUser( @RequestParam Long userId, Model model ){
        model.addAttribute("users",userService.findAll());
        User user = userService.findById(userId).orElse(null);
        if(user!=null){
            model.addAttribute("selectedUser", user);
            model.addAttribute("currentUserTickets",ticketOrderService.findAllByUser(user));
        }
        return "userHistory";
    }

    @GetMapping("/edit-ticket/{ticketId}")
    public String getTicketEditForm( @PathVariable Long ticketId, Model model){
        Optional<TicketOrder> ticketOrderOpt = ticketOrderService.findById(ticketId);
        if(ticketOrderOpt.isEmpty())
            return "redirect:/movies/users";
        TicketOrder ticketOrder = ticketOrderOpt.get();
        model.addAttribute("orderToEdit",ticketOrder);
        model.addAttribute("movies",movieService.findAll());
        return "editOrder";
    }
    @PostMapping("/edited")
    public String saveEditedOrder(@RequestParam Long orderId,
                                  @RequestParam Long numTickets,
                                  @RequestParam String movieTitle
                                  ){
        ticketOrderService.updateOrder(orderId,numTickets,movieTitle);
        return "redirect:/movies/users";
    }
}

