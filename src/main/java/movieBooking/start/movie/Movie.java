package movieBooking.start.movie;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import movieBooking.start.BaseEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
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

    // 장르
    @Enumerated(EnumType.STRING)
    private MovieGenre movieGenre;

    // 러닝타임 (분)
    private Integer runningTime;

    // 배우
    @OneToMany(mappedBy = "movie")
    private List<MovieActor> actors;

    public Movie(String title, LocalDate releaseDate, Director director, MovieGenre movieGenre, Integer runningTime) {
        this.title = title;
        this.releaseDate = releaseDate;
        setDirector(director);
        this.movieGenre = movieGenre;
        this.runningTime = runningTime;
    }

    /* 연관관계 편의 메서드 */
    public void setDirector(Director director) {
        if (this.director != null) {
            this.director.getDirectedProductions().remove(this);
        }
        this.director = director;
        director.getDirectedProductions().add(this);
    }
}
