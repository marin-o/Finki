package mk.finki.ukim.mk.lab.service.impl;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import mk.finki.ukim.mk.lab.model.Movie;
import mk.finki.ukim.mk.lab.model.Production;
import mk.finki.ukim.mk.lab.repository.jpa.MovieRepository;
import mk.finki.ukim.mk.lab.repository.jpa.ProductionRepository;
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
        return movieRepository.findAllBySummaryContains(text);
    }

    @Override
    public List<Movie> searchMovies( String text, double rating ) {
        return movieRepository.findAllBySummaryContainsAndRatingIsGreaterThanEqual(text,rating);
    }

    @Override
    public List<Movie> searchMovies( double rating ) {
        return movieRepository.findAllByRatingIsGreaterThanEqual(rating);
    }

    @Override
    public void deleteById( Long id ) {
        movieRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Optional<Movie> save(Long id, String title, String summary, Double rating, Long production ) {
        Production p = productionRepository.findById(production)
                .orElseThrow(() -> new RuntimeException("Invalid production"));
        if(id != null)
            movieRepository.deleteById(id);
        return Optional.of(movieRepository.save(new Movie(title, summary, rating, p)));
    }
}
