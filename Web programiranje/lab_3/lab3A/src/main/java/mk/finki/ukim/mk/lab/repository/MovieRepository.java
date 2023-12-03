package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Movie;
import mk.finki.ukim.mk.lab.model.Production;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class MovieRepository {

    public List<Movie> findAll(){
        return DataHolder.movies;
    }

    public List<Movie> searchMovies(String text){
        return DataHolder.movies.stream()
                .filter(movie -> movie.getTitle().contains(text) ||
                        movie.getSummary().contains(text))
                .collect(Collectors.toList());
    }

    public List<Movie> searchMovies( String text, double rating ) {
        return DataHolder.movies.stream()
                .filter(movie -> movie.getTitle().contains(text) &&
                        movie.getRating()>=rating)
                .collect(Collectors.toList());
    }

    public List<Movie> searchMovies( double rating ) {
        return DataHolder.movies.stream()
                .filter(movie -> movie.getRating()>=rating)
                .collect(Collectors.toList());
    }

    public void deleteById( Long id ) {
        DataHolder.movies.removeIf(m->m.getId().equals(id));
    }

    public Optional<Movie> findById( Long id ) {
        return DataHolder.movies.stream().filter(m->m.getId().equals(id)).findFirst();
    }

    public Optional<Movie> save( String title, String summary, Double rating, Production production ) {
        DataHolder.movies.removeIf(m->m.getTitle().equals(title));
        Movie m = new Movie(title,summary,rating,production);
        DataHolder.movies.add(m);
        return Optional.of(m);
    }
}
