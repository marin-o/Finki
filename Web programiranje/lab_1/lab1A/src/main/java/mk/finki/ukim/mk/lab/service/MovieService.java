package mk.finki.ukim.mk.lab.service;

import mk.finki.ukim.mk.lab.model.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> listAll();
    List<Movie> searchMovies(String text);
    List<Movie> searchMovies(String text, double rating);
    List<Movie> searchMovies(double rating);
}
