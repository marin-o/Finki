package mk.finki.ukim.mk.lab.web.controller;

import lombok.AllArgsConstructor;
import mk.finki.ukim.mk.lab.model.Movie;
import mk.finki.ukim.mk.lab.service.MovieService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/movies")
@AllArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @GetMapping
    public String getMoviesPage( Model model ){
        model.addAttribute("movies",movieService.findAll());
        return "listMovies";
    }

    @GetMapping("/filter")
    public String getMoviesPageFiltered( @RequestParam(required=false, name="movieText") String text,
                                         @RequestParam(required=false, name="minRating") Double rating,
                                         Model model){
        if(text!=null && rating!=null){
            model.addAttribute("movies",movieService.findAllByTextAndRating(text,rating));
            return "listMovies";
        }
        else if(text!=null){
            model.addAttribute("movies",movieService.findAllByText(text));
            return "listMovies";
        }
        else if(rating!=null){
            model.addAttribute("movies",movieService.findAllByRating(rating));
            return "listMovies";
        }
        model.addAttribute("movies", movieService.findAll());
        model.addAttribute("notFoundError","No movies could be found using that search criteria");
        return "listMovies";
    }

    @GetMapping("/add-form")
    public String getAddForm(){
        return "editMovie";
    }
    @PostMapping("/add")
    public String addNewMovie(@RequestParam String title,
                              @RequestParam String summary,
                              @RequestParam Double rating) {
                              //@RequestParam Long production){
        movieService.save(null, title, summary, rating);
        return "redirect:/movies";
    }

    @GetMapping("/delete/{id}")
    public String deleteMovie( @PathVariable Long id, Model model ){
        movieService.deleteById(id);
        model.addAttribute("movies",movieService.findAll());
        return "listMovies";
    }

    @GetMapping("/edit-form/{id}")
    public String getEditMovieForm(@PathVariable Long id, Model model){
        Optional<Movie> movieOpt = movieService.findById(id);
        if(movieOpt.isPresent()){
            Movie movie = movieOpt.get();
            model.addAttribute("movieToEdit",movie);
        }
        return "editMovie";
    }
    @PostMapping("/edit")
    public String updateMovie(@RequestParam Long movieId,
                              @RequestParam String title,
                              @RequestParam String summary,
                              @RequestParam Double rating){
                              //@RequestParam Long production){
        movieService.save(movieId,title,summary,rating);
        return "redirect:/movies";
    }


}
