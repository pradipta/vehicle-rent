import service.BookingService;
import service.BranchService;
import service.VehicleService;
import service.impl.BookingServiceImpl;
import service.impl.BranchServiceImpl;
import service.impl.VehicleServiceImpl;

import java.util.ArrayList;

/**
 * @author pradipta.sarma
 * @since 04/04/22
 */

public class Driver {
    private final BookingService bookingService;
    private final VehicleService vehicleService;
    private final BranchService branchService;

    public Driver() {
        this.vehicleService = new VehicleServiceImpl();
        this.branchService = new BranchServiceImpl();
        this.bookingService = new BookingServiceImpl(new ArrayList<>(), vehicleService, branchService);
    }

    public static void main(String[] args) {

    }
}
