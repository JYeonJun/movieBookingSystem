package movieBooking.start.theater;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Seat {

    @Id
    @GeneratedValue
    @Column(name = "SEAT_ID")
    private Long id;

    // 행
    private Integer row;

    // 열
    private Integer column;

    // 좌석 상태 [가용 (AVAILABLE), 불가용 (UNAVAILABLE)]
    private SeatState seatState;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MOVIE_ID")
    private Theater theater;
}
