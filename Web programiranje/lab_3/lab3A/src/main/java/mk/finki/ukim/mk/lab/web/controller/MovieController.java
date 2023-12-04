package mk.finki.ukim.mk.lab.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import mk.finki.ukim.mk.lab.model.Movie;
import mk.finki.ukim.mk.lab.service.MovieService;
import mk.finki.ukim.mk.lab.service.ProductionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/movies")
@AllArgsConstructor
public class MovieController {

    private final MovieService movieService;
    private final ProductionService productionService;

    @GetMapping()
    public String getMoviesPage( @RequestParam(required=false) String error, Model model ){
        model.addAttribute("movies",movieService.findAll());
        if(error != null && !error.isEmpty()) {
            model.addAttribute("notFoundError", "Selected movie could not be found, try another movie");
        }
        //Integer userViews = (Integer) req.getServletContext().getAttribute("userViews");
        //req.getServletContext().setAttribute("userViews",++userViews);
        //model.addAttribute("userViews", req.getServletContext().getAttribute("userViews"));

        return "listMovies";
    }

    @GetMapping("/filter")
    public String getMoviesPageFiltered( @RequestParam(required=false) String movieText,
                                 @RequestParam(required=false) String minRating,
                                 Model model, HttpServletRequest req ){
        if(movieText!=null && !movieText.isEmpty()) {
            if(minRating != null && !minRating.isEmpty())
                model.addAttribute("movies", movieService.searchMovies(movieText, Double.parseDouble(minRating)));
            else model.addAttribute("movies", movieService.searchMovies(movieText));
        }
        else if(minRating != null && !minRating.isEmpty()){
            model.addAttribute("movies", movieService.searchMovies(Double.parseDouble(minRating)));
        }
        else
            model.addAttribute("movies",movieService.findAll());
        Integer userViews = (Integer) req.getServletContext().getAttribute("userViews");
        req.getServletContext().setAttribute("userViews",++userViews);
        model.addAttribute("userViews", req.getServletContext().getAttribute("userViews"));
        return "listMovies";
    }

    @GetMapping("/edit-form/{id}")
    public String getEditMovieForm( @PathVariable Long id, Model model ){
        Optional<Movie> m = movieService.findById(id);
        if(m.isPresent()){
            Movie movie = m.get();
            model.addAttribute("productions", productionService.findAll());
            model.addAttribute("movie",movie);
            return "editMovie";
        }

        return "redirect:/movies?error=MovieNotFound";
    }
    @GetMapping("/add-form")
    public String getAddMoviePage( Model model ){
        model.addAttribute("productions", productionService.findAll());
        return "editMovie";
    }

    @GetMapping("/delete/{id}")
    public String deleteMovie( @PathVariable Long id ){
        movieService.deleteById(id);
        return "redirect:/movies";
    }
    @PostMapping("/add")
    public String addMovie( @RequestParam(required = false) Long id,
                            @RequestParam String title,
                            @RequestParam String summary,
                            @RequestParam Double rating,
                            @RequestParam Long production){
        movieService.save(id, title, summary, rating, production);
        return "redirect:/movies";
    }

}
