package movieBooking.start.ticketing;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import movieBooking.start.person.Client;
import movieBooking.start.secreening.Screening;
import movieBooking.start.theater.Seat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Ticketing {

    @Id
    @GeneratedValue
    @Column(name = "TICKETING_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SCREENING_ID")
    private Screening screening;

    // 한 예매에는 여러 개의 좌석을 선택할 수 있다.
    @OneToMany(mappedBy = "ticketing")
    private List<Seat> reservationSeats = new ArrayList<>();

    // 예매는 취소가 가능하다.
    @Enumerated(EnumType.STRING)
    private TicketingState ticketingState;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENT_ID")
    private Client client;

    public Ticketing(Screening screening, Client client) {
        this.screening = screening;
        this.ticketingState = TicketingState.RESERVATION;
        setClient(client);
    }

    /* 연관관계 편의 메서드 */
    public void setClient(Client client) {
        if (this.client != null) {
            this.client.getTicketingList().remove(this);
        }
        this.client = client;
        client.getTicketingList().add(this);
    }

    /* 비지니스 로직 */
    public void cancel() {
        this.ticketingState = TicketingState.CANCEL;
    }
}
