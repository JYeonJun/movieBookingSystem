package movieBooking.start.movie;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Director {

    @Id
    @GeneratedValue
    @Column(name = "DIRECTOR_ID")
    private Long id;

    private String name;

    private LocalDate birthDate;

    // 출생지
    private String placeBirth;

    @OneToMany(mappedBy = "director")
    private List<Movie> directedProductions = new ArrayList<>();

    public Director(String name, LocalDate birthDate, String placeBirth) {
        this.name = name;
        this.birthDate = birthDate;
        this.placeBirth = placeBirth;
    }
}
