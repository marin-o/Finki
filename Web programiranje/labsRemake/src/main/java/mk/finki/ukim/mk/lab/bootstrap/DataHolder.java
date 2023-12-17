package mk.finki.ukim.mk.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.mk.lab.model.Movie;
import mk.finki.ukim.mk.lab.model.TicketOrder;
import mk.finki.ukim.mk.lab.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {
    public static List<Movie> movies;
    public static List<TicketOrder> ticketOrders;
    public static List<User> users;

    @PostConstruct
    private void init(){
        movies = new ArrayList<>();
        ticketOrders = new ArrayList<>();
        users = new ArrayList<>();

        movies.add(new Movie("The Shawshank Redemption", "Two imprisoned men bond over several years, finding solace and redemption.", 9.3));
        movies.add(new Movie("The Godfather", "The aging patriarch of a crime dynasty transfers control to his reluctant son.", 9.2));
        movies.add(new Movie("The Dark Knight", "Batman faces the Joker's havoc and chaos in Gotham.", 9.0));
        movies.add(new Movie("Schindler's List", "Industrialist Oskar Schindler saves Jewish workers during World War II.", 8.9));
        movies.add(new Movie("Pulp Fiction", "Intersecting stories of mob hitmen, a boxer, and two diner bandits.", 8.9));
        movies.add(new Movie("The Lord of the Rings", "Gandalf and Aragorn lead the fight against Sauron's army.", 8.9));
        movies.add(new Movie("Forrest Gump", "An Alabama man experiences historical events from the 1950s to the 1980s.", 8.8));
        movies.add(new Movie("Inception", "A thief plants an idea into a CEO's mind.", 8.7));
        movies.add(new Movie("The Matrix", "A computer hacker fights against controllers of his reality.", 8.7));
        movies.add(new Movie("Titanic", "A love story on the ill-fated R.M.S. Titanic.", 7.8));
    }
}
