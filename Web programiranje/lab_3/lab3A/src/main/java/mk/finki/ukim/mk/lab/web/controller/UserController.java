package mk.finki.ukim.mk.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import mk.finki.ukim.mk.lab.embeddable.UserAddress;
import mk.finki.ukim.mk.lab.model.Movie;
import mk.finki.ukim.mk.lab.model.ShoppingCart;
import mk.finki.ukim.mk.lab.model.TicketOrder;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.service.MovieService;
import mk.finki.ukim.mk.lab.service.TicketOrderService;
import mk.finki.ukim.mk.lab.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/movies/users")
public class UserController {
    private final TicketOrderService ticketOrderService;
    private final UserService userService;
    private final MovieService movieService;

    @GetMapping
    public String getUsersPage(HttpServletRequest req, Model model) {
        List<User> users = userService.listAll();
        model.addAttribute("users", users);
        String username = req.getParameter("username");
        if (username != null && !username.isEmpty()) {
            User u = userService.findUser(username);
            List<ShoppingCart> carts = u.getCarts();
            List<TicketOrder> ticketOrders = new ArrayList<>();
            for (ShoppingCart cart :
                    carts) {
                ticketOrders.addAll(cart.getTicketOrders());
            }
            model.addAttribute("currentUserTickets",ticketOrders);
            model.addAttribute("selectedUser", u);
        }
        return "userHistory";
    }

    @GetMapping("/edit-form/{id}")
    public String editOrder(@PathVariable Long id, Model model) {
        Optional<TicketOrder> o = ticketOrderService.findById(id);
        if (o.isPresent()) {
            TicketOrder order = o.get();
            model.addAttribute("order", order);
            List<Movie> movies = movieService.findAll();
            model.addAttribute("movies", movies);
            return "editOrder";
        }
        return "userHistory";
    }

    @PostMapping
    public String postSomething(@RequestParam(required = false) String username) {
        if (username != null)
            return "redirect:/movies/users?&username=" + username;
        else return "redirect:/movies/users";
    }

    @PostMapping("/edited")
    public String editedMovie(@RequestParam Long id, @RequestParam String movTitle, @RequestParam Long numTickets) {
        Optional<TicketOrder> editedOrder = ticketOrderService.findById(id);
        String username = editedOrder.get().getUsername();
        ticketOrderService.deletebyId(id);
        ticketOrderService.saveOrder(movTitle, String.valueOf(numTickets),username);
        return "redirect:/movies/users";
    }

    @GetMapping("/registration-form")
    public String registrationForm() {
        return "registration";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username,
                               @RequestParam String name,
                               @RequestParam String surname,
                               @RequestParam String password) {
        // You may want to add validation and error handling here before saving the user.
        User user = new User(username, name, surname, password, LocalDate.now(),new UserAddress("drzava","grad","adr1","adr2"));
        userService.save(user);
        return "redirect:/movies";
    }
}

