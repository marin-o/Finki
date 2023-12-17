package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    List<Movie> findAll();
    List<Movie> findAllByText( String text );
    List<Movie> findAllByRating( Double rating );
    List<Movie> findAllByTextAndRating( String text, double rating );

    void deleteById( Long id );

    Optional<Movie> findById( Long id );

    void save( Long movieId,
               String title,
               String summary,
               Double rating );
}
