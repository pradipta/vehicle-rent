package service.impl;

import lombok.AllArgsConstructor;
import model.Booking;
import model.BookingState;
import model.Branch;
import model.Vehicle;
import model.VehicleType;
import service.BookingService;
import service.BranchService;
import service.VehicleService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author pradipta.sarma
 * @since 31/03/22
 */

@AllArgsConstructor
public class BookingServiceImpl implements BookingService {
    private List<Booking> allBookings;
    private VehicleService vehicleService;
    private BranchService branchService;

    @Override
    public Boolean book(String branchName, VehicleType vehicleType, int startTime, int endTime) throws Exception {
        if (startTime >= endTime) {
            throw new Exception("Invalid time bounds passed");
        }

        Branch branch = branchService.get(branchName).orElseThrow(() -> new Exception("Branch not found"));
        List<Vehicle> vehicles = branchService.getVehiclesOfType(branch, vehicleType);

        Vehicle selectedVehicle = null;

        for (Vehicle vehicle : vehicles) {
            if (isAvailable(vehicle, startTime, endTime)) {
                selectedVehicle = selectThisVehicle(vehicle, startTime, endTime);
                break;
            }
        }

        if (selectedVehicle == null) {
            throw new Exception("No vehicles for the slots found");
        }

        Booking booking = Booking.builder()
                .branch(branch)
                .startTime(startTime)
                .endTime(endTime)
                .state(BookingState.ACTIVE)
                .vehicle(selectedVehicle)
                .build();

        allBookings.add(booking);

        return true;
    }

    private Vehicle selectThisVehicle(Vehicle vehicle, int startTime, int endTime) {
        for (int i = startTime; i <= endTime; i++) {
            vehicle.getAvailabilityMap().put(i, false);
        }
        return vehicle;
    }

    private boolean isAvailable(Vehicle vehicle, int startTime, int endTime) {
        for (int i = startTime; i <= endTime; i++) {
            if (vehicle.getAvailabilityMap().containsKey(i) && !vehicle.getAvailabilityMap().get(i)) {
                return false;
            }
        }
        return true;
    }
}
