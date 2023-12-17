package mk.finki.ukim.mk.lab.repository;

import mk.finki.ukim.mk.lab.bootstrap.DataHolder;
import mk.finki.ukim.mk.lab.model.Movie;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class MovieRepository {
    public List<Movie> findAll(){
        return DataHolder.movies;
    }

    public List<Movie> findAllByText( String text ){
        return DataHolder.movies.stream().filter(m->m.getTitle().contains(text) || m.getSummary().contains(text)).collect(Collectors.toList());
    }

    public List<Movie> findAllByRating( double rating ){
        return DataHolder.movies.stream().filter(m->m.getRating()>=rating).collect(Collectors.toList());
    }

    public List<Movie> findAllByTextAndRating( String text, double rating){
        return DataHolder.movies.stream().filter(m->m.getRating()>=rating && (m.getTitle().contains(text) || m.getSummary().contains(text))).collect(Collectors.toList());
    }

    public void deleteById( Long id ) {
        DataHolder.movies.removeIf(m->m.getId().equals(id));
    }

    public Optional<Movie> findById( Long id ) {
        return DataHolder.movies.stream().filter(m->m.getId().equals(id)).findFirst();
    }

    public void save( Movie movie ) {
        DataHolder.movies.removeIf(m->m.getId().equals(movie.getId()));
        DataHolder.movies.add(movie);
    }
}
