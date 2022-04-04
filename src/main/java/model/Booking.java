package model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

/**
 * @author pradipta.sarma
 * @since 04/04/22
 */
@Data
@Builder
public class Booking {
    private Vehicle vehicle;
//    private Set<Slot> slots;
    private int startTime;
    private int endTime;
    private BigDecimal price;
    private Branch branch;
    private BookingState state;
}
