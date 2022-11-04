package movieBooking.start.ticketing;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import movieBooking.start.theater.Seat;
import movieBooking.start.theater.Theater;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ReservationSeat {

    @Id
    @GeneratedValue
    @Column(name = "RESERVATION_SEAT_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TICKETING_ID")
    private Ticketing ticketing;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SEAT_ID")
    private Seat seat;

    public ReservationSeat(Ticketing ticketing, Seat seat) {
        this.ticketing = ticketing;
        this.seat = seat;
    }

    /* 연관관계 편의 메서드 */
    /*public void setTicketing(Ticketing ticketing) {
        if (this.ticketing != null) {
            this.ticketing.getReservationSeats().remove(this);
        }
        this.ticketing = ticketing;
        ticketing.getReservationSeats().add(this);
    }*/
}
