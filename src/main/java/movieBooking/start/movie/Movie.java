package movieBooking.start.movie;

import lombok.Getter;
import lombok.Setter;
import movieBooking.start.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class Movie extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "MOVIE_ID")
    private Long id;

    // 영화 제목
    private String title;

    // 개봉일
    private LocalDate releaseDate;

    // 감독
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DIRECTO_ID")
    private Director director;

    // 배우
    private List<Actor> actors;

    // 장르
    @Enumerated(EnumType.STRING)
    private MovieGenre movieGenre;

    // 러닝타임 (분)
    private Integer runningTime;
}
