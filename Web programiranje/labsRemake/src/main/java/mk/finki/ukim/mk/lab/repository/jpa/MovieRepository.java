package mk.finki.ukim.mk.lab.repository.jpa;

import mk.finki.ukim.mk.lab.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    public List<Movie> findAllByTitleContains( String text );
    List<Movie> findAllByRatingGreaterThan( Double rating );

    List<Movie> findAllByTitleContainsAndRatingGreaterThan( String text, double rating );
}
