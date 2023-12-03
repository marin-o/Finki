package mk.finki.ukim.mk.lab.service.impl;

import lombok.AllArgsConstructor;
import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Movie;
import mk.finki.ukim.mk.lab.model.Production;
import mk.finki.ukim.mk.lab.repository.MovieRepository;
import mk.finki.ukim.mk.lab.repository.ProductionRepository;
import mk.finki.ukim.mk.lab.service.MovieService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final ProductionRepository productionRepository;

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Optional<Movie> findById( Long id ) {
        return movieRepository.findById(id);
    }

    @Override
    public List<Movie> searchMovies( String text ) {
        return movieRepository.searchMovies(text);
    }

    @Override
    public List<Movie> searchMovies( String text, double rating ) {
        return movieRepository.searchMovies(text,rating);
    }

    @Override
    public List<Movie> searchMovies( double rating ) {
        return movieRepository.searchMovies(rating);
    }

    @Override
    public void deleteById( Long id ) {
        movieRepository.deleteById(id);
    }

    @Override
    public Optional<Movie> save( String title, String summary, Double rating, Long production ) {
        Production p = productionRepository.findById(production)
                .orElseThrow(() -> new RuntimeException("Invalid production"));
        DataHolder.movies.removeIf(m->m.getTitle().equals(title));
        return this.movieRepository.save(title,summary,rating,p);

    }
}
