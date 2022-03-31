package service;

import model.VehicleType;

/**
 * @author pradipta.sarma
 * @since 31/03/22
 */
public interface BookingService {
    Boolean book(String branch, VehicleType vehicleType, int startTime, int endTime);
}
