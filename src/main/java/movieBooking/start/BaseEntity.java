package movieBooking.start;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
public class BaseEntity {

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;
}
