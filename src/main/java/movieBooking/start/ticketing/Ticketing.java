package movieBooking.start.ticketing;

import lombok.Getter;
import lombok.Setter;
import movieBooking.start.secreening.Screening;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Ticketing {

    @Id
    @GeneratedValue
    @Column(name = "TICKETING_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SCREENING_ID")
    private Screening screening;

    // 한 예매에는 여러 개의 좌석을 선택할 수 있다.

    // 예매는 취소가 가능하다.
    @Enumerated(EnumType.STRING)
    private TicketingState ticketingState;
}
