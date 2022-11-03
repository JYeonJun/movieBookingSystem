package movieBooking.start.person;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import movieBooking.start.ticketing.Ticketing;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("C")
public class Client extends Person {

    // 마일리지
    private Integer mileage;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Ticketing> ticketingList = new ArrayList<>();

    public Client(LocalDateTime createdDate, LocalDateTime lastModifiedDate, String name, LocalDate birthDate, Address address, Integer mileage) {
        super(createdDate, lastModifiedDate, name, birthDate, address);
        this.mileage = mileage;
    }
}
