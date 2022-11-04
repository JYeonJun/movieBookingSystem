package movieBooking.start.theater;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
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
    private List<Seat> seats = new ArrayList<>();

    public Theater(String name, Integer floor) {
        this.name = name;
        this.floor = floor;
    }

    @Override
    public String toString() {
        return "Theater{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", floor=" + floor +
                '}';
    }
}

