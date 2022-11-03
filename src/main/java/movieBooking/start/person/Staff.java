package movieBooking.start.person;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@DiscriminatorValue("S")
public class Staff extends Person {

    // 근무시작일
    private LocalDate workStartDate;
}
