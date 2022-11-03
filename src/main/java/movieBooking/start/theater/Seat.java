package movieBooking.start.theater;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Seat {

    @Id
    @GeneratedValue
    @Column(name = "SEAT_ID")
    private Long id;

    // 행
    private Integer lineNumber;

    // 열
    private Integer columnNumber;

    // 좌석 상태 [가용 (AVAILABLE), 불가용 (UNAVAILABLE)]
    @Enumerated(EnumType.STRING)
    private SeatState seatState;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "THEATER_ID")
    private Theater theater;

    public Seat(Integer lineNumber, Integer columnNumber, SeatState seatState, Theater theater) {
        this.lineNumber = lineNumber;
        this.columnNumber = columnNumber;
        this.seatState = seatState;
        setTheater(theater);
    }

    /* 연관관계 편의 메서드*/
    public void setTheater(Theater theater) {
        if (this.theater != null) {
            this.theater.getSeats().remove(this);
        }
        this.theater = theater;
        theater.getSeats().add(this);
    }
}
