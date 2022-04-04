import model.Vehicle;
import model.VehicleType;
import service.impl.BookingServiceImpl;
import service.impl.BranchServiceImpl;
import service.impl.VehicleServiceImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author pradipta.sarma
 * @since 04/04/22
 */

public class Driver {
    public static void main(String[] args) throws Exception {
        var branchService = new BranchServiceImpl();
        var vehicleService = new VehicleServiceImpl(new HashSet<>(), branchService);
        var bookingService = new BookingServiceImpl(new ArrayList<>(), vehicleService, branchService);

        branchService.add("B1", Set.of(VehicleType.CAR, VehicleType.BIKE, VehicleType.VAN));
        vehicleService.add("B1", VehicleType.CAR, 500.00);
        vehicleService.add("B1", VehicleType.CAR, 1000.00);
        vehicleService.add("B1", VehicleType.BIKE, 250.00);
        vehicleService.add("B1", VehicleType.BIKE, 300.00);
        try {
            vehicleService.add("B1", VehicleType.BUS, 2500.00);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println(bookingService.book("B1", VehicleType.VAN, 1, 4));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println(bookingService.book("B1", VehicleType.CAR, 1, 3).get().getPrice());
        System.out.println(bookingService.book("B1", VehicleType.BIKE, 2, 3).get().getPrice());
        System.out.println(bookingService.book("B1", VehicleType.BIKE, 2, 5).get().getPrice());

        vehicleService.getVehiclesByBranchAndTime("B1", 1, 5)
                .stream().map(vehicle -> vehicle.getId())
                .forEach(System.out::println);
    }
}
