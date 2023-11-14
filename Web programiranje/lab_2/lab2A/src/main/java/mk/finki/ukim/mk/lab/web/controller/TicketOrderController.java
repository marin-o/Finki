package mk.finki.ukim.mk.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import mk.finki.ukim.mk.lab.model.TicketOrder;
import mk.finki.ukim.mk.lab.service.TicketOrderService;
import mk.finki.ukim.mk.lab.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/movies/ticketOrder")
@AllArgsConstructor
public class TicketOrderController {
    private final TicketOrderService ticketOrderService;
    private final UserService userService;

    @GetMapping()
    public String getTicketOrderPage( @RequestParam(required=false, defaultValue="Invalid movie") String movieTitle,
                                      @RequestParam(required=false, defaultValue="-1") String numTickets,
                                      @RequestParam(required=false, defaultValue="Invalid username") String username,
                                      HttpServletRequest req,
                                      Model model ){
        TicketOrder ticket = ticketOrderService.placeOrder(
                movieTitle,
                username,req.getRemoteAddr(),
                Integer.parseInt(numTickets)
        );
        userService.addTicketToUser(username,ticket);
        model.addAttribute("ticket", ticket);
        return "orderConfirmation";
    }

    @PostMapping()
    public String buyTicket(@RequestParam String movieTitle,
                            @RequestParam String numTickets,
                            @RequestParam String username){
        return "redirect:/movies/ticketOrder+?movieTitle="+movieTitle+"&tickets="+numTickets+"&username="+username;
    }
}
