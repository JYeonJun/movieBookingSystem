package movieBooking.start.person;

import lombok.*;

import javax.persistence.Embeddable;

@Embeddable
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Address {

    private String city;

    private String street;

    private String zipcode;
}
