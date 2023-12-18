package mk.finki.ukim.mk.lab.embeddable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class UserAddress {
    private String country;
    private String city;
    private String address1;
    private String address2;
}
