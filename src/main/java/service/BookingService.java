package service;

import model.Booking;
import model.VehicleType;

import java.util.Optional;

/**
 * @author pradipta.sarma
 * @since 31/03/22
 */
public interface BookingService {
    Optional<Booking> book(String branch, VehicleType vehicleType, int startTime, int endTime) throws Exception;
}
