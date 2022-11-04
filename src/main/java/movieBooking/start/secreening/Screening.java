package movieBooking.start.secreening;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import movieBooking.start.movie.Movie;
import movieBooking.start.theater.Theater;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
// 고객이 예매를 위해 상영을 조회할 경우, 전체좌석과 예매 가능한 좌석을 함께 보여줘야 한다.
public class Screening {

    @Id
    @GeneratedValue
    @Column(name = "SCREENING_ID")
    private Long id;

    // 영화 이름
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MOVIE_ID")
    private Movie movie;

    // 시작시간
    private LocalTime startTime;

    // 종료시간
    // 종료시간 = 시작시간.plusMinutes(movie.getRunningTime())
    private LocalTime endTime;

    // 상영관 이름/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "THEATER_ID")
    private Theater theater;

    public Screening(Movie movie, LocalTime startTime, Theater theater) {
        this.movie = movie;
        this.startTime = startTime;
        this.endTime = calEndTime();
        this.theater = theater;
    }

    @Override
    public String toString() {
        return "Screening{" +
                "screening.id() = " + id +
                ", movie.title() = " + movie.getTitle() +
                ", startTime = " + startTime +
                ", endTime = " + endTime +
                '}';
    }

    /* 로직 */
    public LocalTime calEndTime() {
        return startTime.plusMinutes(movie.getRunningTime());
    }
}
