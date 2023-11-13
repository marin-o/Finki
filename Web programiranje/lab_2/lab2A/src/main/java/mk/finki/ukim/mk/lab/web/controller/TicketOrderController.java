package mk.finki.ukim.mk.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import mk.finki.ukim.mk.lab.model.TicketOrder;
import mk.finki.ukim.mk.lab.service.TicketOrderService;
import mk.finki.ukim.mk.lab.service.UserService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/movies/ticketOrder")
@AllArgsConstructor
public class TicketOrderController {
    private final TicketOrderService ticketOrderService;
    private final UserService userService;

    @GetMapping()
    public String getTicketOrderPage(
                                      HttpServletRequest request, Model model ){
        TicketOrder ticket = ticketOrderService.placeOrder(
                request.getParameter("movieTitle"),
                request.getParameter("username"),
                request.getRemoteAddr(),
                Integer.parseInt(request.getParameter("tickets"))
        );
        userService.addTicketToUser(request.getParameter("username"),ticket);
        model.addAttribute("ticket",ticket);
        return "orderConfirmation";
    }

    @PostMapping()
    public String buyTicket(@RequestParam String movieTitle,
                            @RequestParam String numTickets,
                            @RequestParam String username){
        return "redirect:/movies/ticketOrder+?movieTitle="+movieTitle+"&tickets="+numTickets+"&username="+username;
    }
}
