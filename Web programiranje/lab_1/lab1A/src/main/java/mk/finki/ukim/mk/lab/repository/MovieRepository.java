package mk.finki.ukim.mk.lab.repository;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Movie;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MovieRepository {
    List<Movie> movies = new ArrayList<>();

    @PostConstruct
    private void init(){
        this.movies = Arrays.asList(
                new Movie("The Shawshank Redemption", "Two imprisoned men bond over several years, finding solace and redemption.", 9.3),
                new Movie("The Godfather", "The aging patriarch of a crime dynasty transfers control to his reluctant son.", 9.2),
                new Movie("The Dark Knight", "Batman faces the Joker's havoc and chaos in Gotham.", 9.0),
                new Movie("Schindler's List", "Industrialist Oskar Schindler saves Jewish workers during World War II.", 8.9),
                new Movie("Pulp Fiction", "Intersecting stories of mob hitmen, a boxer, and two diner bandits.", 8.9),
                new Movie("The Lord of the Rings", "Gandalf and Aragorn lead the fight against Sauron's army.", 8.9),
                new Movie("Forrest Gump", "An Alabama man experiences historical events from the 1950s to the 1980s.", 8.8),
                new Movie("Inception", "A thief plants an idea into a CEO's mind.", 8.7),
                new Movie("The Matrix", "A computer hacker fights against controllers of his reality.", 8.7),
                new Movie("Titanic", "A love story on the ill-fated R.M.S. Titanic.", 7.8));
    }

    public List<Movie> findAll(){
        return movies;
    }

    public List<Movie> searchMovies(String text){
        return movies.stream()
                .filter(movie -> movie.getTitle().contains(text) ||
                        movie.getSummary().contains(text))
                .collect(Collectors.toList());
    }

    public List<Movie> searchMovies( String text, double rating ) {
        return movies.stream()
                .filter(movie -> movie.getTitle().contains(text) &&
                        movie.getRating()>=rating)
                .collect(Collectors.toList());
    }

    public List<Movie> searchMovies( double rating ) {
        return movies.stream()
                .filter(movie -> movie.getRating()>=rating)
                .collect(Collectors.toList());
    }
}
