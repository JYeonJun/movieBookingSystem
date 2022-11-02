package movieBooking.start.movie;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class Actor {

    @Id
    @GeneratedValue
    @Column(name = "ACTOR_ID")
    private Long id;

    private String name;

    private LocalDate birthDate;

    @OneToMany(mappedBy = "actor")
    private List<MovieActor> productionCasts;
}
