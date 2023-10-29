package mk.finki.ukim.mk.lab.repository;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Movie;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MovieRepository {
    List<Movie> movies = new ArrayList<>();

    @PostConstruct
    private void init(){
        java.util.Random random = new java.util.Random();
        for ( int i=0; i < 10; i++ ) {
            movies.add(
                    new Movie("Movie " + i,
                            "Summary for movie " + i,
                            random.nextDouble(0,100)
                    ));
        }
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
