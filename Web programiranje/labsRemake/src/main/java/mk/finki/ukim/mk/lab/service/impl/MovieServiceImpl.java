package mk.finki.ukim.mk.lab.service.impl;

import lombok.AllArgsConstructor;
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
    public List<Movie> findAllByText( String text ) {
        return movieRepository.findAllByText(text);
    }

    @Override
    public List<Movie> findAllByRating( Double rating ) {
        return movieRepository.findAllByRating(rating);
    }

    @Override
    public List<Movie> findAllByTextAndRating( String text, double rating ) {
        return movieRepository.findAllByTextAndRating(text, rating);
    }

    @Override
    public void deleteById( Long id ) {
        movieRepository.deleteById(id);
    }

    @Override
    public Optional<Movie> findById( Long id ) {
        return movieRepository.findById(id);
    }

    @Override
    public void save( Long movieId,
                      String title,
                      String summary,
                      Double rating,
                      Long productionId) {
        Production production = productionRepository.findById(productionId);
        if(movieId != null) {
            Optional<Movie> movieOpt=movieRepository.findById(movieId);
            if ( movieOpt.isPresent() ) {
                Movie movie=movieOpt.get();
                movie.setTitle(title);
                movie.setSummary(summary);
                movie.setRating(rating);
                movie.setProduction(production);
                movieRepository.save(movie);
            }
        } else{
            movieRepository.save(new Movie(title, summary, rating,production));
        }
    }
}
