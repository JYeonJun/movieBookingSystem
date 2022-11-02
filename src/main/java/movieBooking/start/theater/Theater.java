package movieBooking.start.theater;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Theater {

    @Id
    @GeneratedValue
    @Column(name = "THEATER_ID")
    private Long id;

    // 상영관 이름 ex) 1상영관, 2상영관
    private String name;

    // 층
    private Integer floor;

    @OneToMany(mappedBy = "theater")
    private List<Seat> seats;
}

