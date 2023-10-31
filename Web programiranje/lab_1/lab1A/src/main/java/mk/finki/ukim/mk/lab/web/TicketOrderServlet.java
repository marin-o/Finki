package mk.finki.ukim.mk.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import mk.finki.ukim.mk.lab.model.TicketOrder;
import mk.finki.ukim.mk.lab.service.impl.TicketOrderServiceImpl;
import mk.finki.ukim.mk.lab.service.impl.UserServiceImpl;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(urlPatterns="/ticketOrder")
@AllArgsConstructor
public class TicketOrderServlet extends HttpServlet {

    private final TicketOrderServiceImpl ticketOrderService;
    private final SpringTemplateEngine templateEngine;
    private final UserServiceImpl userService;
    @Override
    protected void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req,resp);
        WebContext context = new WebContext(webExchange);
        String title = req.getParameter("movieTitle");
        String tickets = req.getParameter("tickets");
        String username = req.getParameter("username");
        if (title == null || title.isEmpty() || title.equals("null"))
            title = "Invalid movie";
        if(tickets == null || tickets.isEmpty())
            tickets = "-1";
        TicketOrder ticket = ticketOrderService.placeOrder(
                title,
                username,req.getRemoteAddr(),
                Integer.parseInt(tickets)
        );
        userService.addTicketToUser(username,ticket);
        context.setVariable("ticket", ticket);
        templateEngine.process("orderConfirmation.html",context,resp.getWriter());
    }

    @Override
    protected void doPost( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
        String movie = req.getParameter("movieTitle");
        String tickets = req.getParameter("numTickets");
        String user = req.getParameter("username");

        resp.sendRedirect("/ticketOrder?movieTitle="+movie+"&tickets="+tickets+"&username="+user);
    }
}
