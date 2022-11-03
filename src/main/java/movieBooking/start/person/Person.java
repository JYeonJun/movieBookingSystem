package movieBooking.start.person;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import movieBooking.start.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
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

    public Person(LocalDateTime createdDate, LocalDateTime lastModifiedDate, String name, LocalDate birthDate, Address address) {
        super(createdDate, lastModifiedDate);
        this.name = name;
        this.birthDate = birthDate;
        this.address = address;
    }
}
