package movieBooking.start.movie;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class Director {

    @Id
    @GeneratedValue
    @Column(name = "DIRECTOR_ID")
    private Long id;

    private String name;

    private LocalDate birthDate;

    @OneToMany(mappedBy = "director")
    private List<Movie> directedProductions;

    // 출생지
    private String placeBirth;
}
