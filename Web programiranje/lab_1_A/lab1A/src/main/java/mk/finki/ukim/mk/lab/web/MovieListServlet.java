package mk.finki.ukim.mk.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.finki.ukim.mk.lab.service.impl.MovieServiceImpl;
import mk.finki.ukim.mk.lab.service.impl.TicketOrderServiceImpl;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;
import java.io.IOException;

@WebServlet(urlPatterns="")
public class MovieListServlet extends HttpServlet {
    private final MovieServiceImpl movieService;
    private final TicketOrderServiceImpl ticketOrderService;
    private final SpringTemplateEngine templateEngine;

    public MovieListServlet( MovieServiceImpl movieService, TicketOrderServiceImpl ticketOrderService,
                             SpringTemplateEngine templateEngine) {
        this.movieService=movieService;
        this.ticketOrderService = ticketOrderService;
        this.templateEngine=templateEngine;
    }

    @Override
    protected void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
        IWebExchange webExchange =JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req,resp);
        WebContext context = new WebContext(webExchange);
        String movieTextContains = req.getParameter("movieText");
        String movieRating = req.getParameter("rating");
        if(movieTextContains!=null && !movieTextContains.isEmpty()) {
            if(movieRating != null && !movieRating.isEmpty())
                context.setVariable("movies", movieService.searchMovies(movieTextContains, Double.parseDouble(movieRating)));
            else context.setVariable("movies", movieService.searchMovies(movieTextContains));
        }
        else if(movieRating != null && !movieRating.isEmpty()){
            context.setVariable("movies", movieService.searchMovies(Double.parseDouble(movieRating)));
        }
        else
            context.setVariable("movies",movieService.listAll());
        templateEngine.process("listMovies.html",context,resp.getWriter());
    }

    @Override
    protected void doPost( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
        String movieText = req.getParameter("movieText");
        String rating = req.getParameter("minRating");

        resp.sendRedirect("/?movieText="+movieText+"&rating="+rating);
    }
}
