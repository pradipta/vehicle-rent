package service.impl;

import factory.PricingServiceFactory;
import lombok.AllArgsConstructor;
import model.Booking;
import model.BookingState;
import model.Branch;
import model.Vehicle;
import model.VehicleType;
import service.BookingService;
import service.BranchService;
import service.PricingService;
import service.VehicleService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * @author pradipta.sarma
 * @since 31/03/22
 */

@AllArgsConstructor
public class BookingServiceImpl implements BookingService {
    private List<Booking> allBookings;
    private VehicleService vehicleService;
    private BranchService branchService;
    private PricingServiceFactory pricingServiceFactory;

    @Override
    public Optional<Booking> book(String branchName, VehicleType vehicleType, int startTime, int endTime) throws Exception {
        if (startTime >= endTime) {
            throw new Exception("Invalid time bounds passed");
        }

        Branch branch = branchService.get(branchName).orElseThrow(() -> new Exception("Branch not found"));
        List<Vehicle> vehicles = branchService.getVehiclesOfType(branch, vehicleType);

        Vehicle selectedVehicle = null;

        for (Vehicle vehicle : vehicles) {
            if (vehicleService.isAvailable(vehicle, startTime, endTime)) {
                selectedVehicle = selectThisVehicle(vehicle, startTime, endTime);
                break;
            }
        }

        if (selectedVehicle == null) {
            System.out.println(-1);
            throw new Exception("No vehicles for the slots found");
        }

        BigDecimal price = computePrice(startTime, endTime, selectedVehicle, branch);

        Booking booking = Booking.builder()
                .branch(branch)
                .startTime(startTime)
                .endTime(endTime)
                .state(BookingState.ACTIVE)
                .vehicle(selectedVehicle)
                .price(price)
                .build();

        allBookings.add(booking);
        return Optional.of(booking);
    }

    private BigDecimal computePrice(int startTime, int endTime, Vehicle selectedVehicle, Branch branch) throws Exception {
        PricingService pricingService = pricingServiceFactory.get("DYNAMIC");
        return pricingService.computePrice(selectedVehicle, startTime, endTime, branch);
    }

    private Vehicle selectThisVehicle(Vehicle vehicle, int startTime, int endTime) {
        for (int i = startTime; i <= endTime; i++) {
            vehicle.getAvailabilityMap().put(i, false);
        }
        return vehicle;
    }
}
