package mk.finki.ukim.mk.lab.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String summary;
    private double rating;
    @OneToOne
    private Production production;

    public Movie( String title, String summary, double rating, Production production ) {
        this.title=title;
        this.summary=summary;
        this.rating=rating;
        this.production = production;
    }

    @Override
    public String toString() {
        return String.format("Title: %s, Summary: %s, Rating: %f",title,summary,rating);
    }
}
