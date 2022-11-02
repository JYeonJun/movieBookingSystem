package movieBooking.start.person;

import lombok.Getter;
import lombok.Setter;
import movieBooking.start.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "DTYPE")
public abstract class Person extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "PERSON_ID")
    private Long id;

    private String name;

    private LocalDate birthDate;

    @Embedded
    private Address address;
}
