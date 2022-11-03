package movieBooking.start.movie;

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
public class Actor {

    @Id
    @GeneratedValue
    @Column(name = "ACTOR_ID")
    private Long id;

    private String name;

    private LocalDate birthDate;

    // 키
    private Double height;

    // 소속사 이름
    private String agency;

    @OneToMany(mappedBy = "actor")
    private List<MovieActor> productionCasts = new ArrayList<>();

    public Actor(String name, LocalDate birthDate, Double height, String agency) {
        this.name = name;
        this.birthDate = birthDate;
        this.height = height;
        this.agency = agency;
    }
}
