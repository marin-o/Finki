package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    List<Movie> findAll();
    Optional<Movie> findById(Long id);
    List<Movie> searchMovies(String text);
    List<Movie> searchMovies(String text, double rating);
    List<Movie> searchMovies(double rating);

    void deleteById( Long id );

    Optional<Movie> save( String title, String summary, Double rating, Long production );
}
