package mk.finki.ukim.mk.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import mk.finki.ukim.mk.lab.model.User;
import mk.finki.ukim.mk.lab.service.impl.UserServiceImpl;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/users")
@AllArgsConstructor
public class UserServlet extends HttpServlet {
    private final UserServiceImpl userService;
    private final SpringTemplateEngine templateEngine;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req,resp);
        WebContext context = new WebContext(webExchange);
        List<User> users = userService.listAll();
        context.setVariable("users",users);
        String username = req.getParameter("username");
        if(username != null && !username.isEmpty()) {
            User u = userService.findUser(username);
            context.setVariable("currentUserTickets", u.getOrders());
            context.setVariable("selectedUser", u);
        }
        templateEngine.process("userHistory.html",context,resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        if(username != null)
            resp.sendRedirect("/users?&username="+username);
        else
            resp.sendRedirect("/users");
    }
}
