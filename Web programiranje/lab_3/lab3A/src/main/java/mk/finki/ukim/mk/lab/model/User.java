package mk.finki.ukim.mk.lab.model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.finki.ukim.mk.lab.converters.UserFullNameConverter;
import mk.finki.ukim.mk.lab.embeddable.UserAddress;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "movie-user")
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @Convert(converter = UserFullNameConverter.class)
    private UserFullName fullName;
    private String password;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfBirth;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<ShoppingCart> carts;
    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "country", column = @Column(name = "user_country")),
            @AttributeOverride( name = "city", column = @Column(name = "user_city")),
            @AttributeOverride( name = "address1", column = @Column(name = "user_address1")),
            @AttributeOverride( name = "address2", column = @Column(name = "user_address2"))
    })
    private UserAddress userAddress;


    public User(String username) {
        this.username = username;
    }
    public User(String username, String name, String surname, String password, LocalDate dateOfBirth, UserAddress userAddress) {
        this.username = username;
        this.fullName= new UserFullName(name, surname);
        this.password=password;
        this.dateOfBirth=dateOfBirth;
        this.carts = new ArrayList<>();
        this.userAddress = userAddress;
    }
}
