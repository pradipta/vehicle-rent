package service.impl;

import model.Branch;
import model.Vehicle;
import service.PricingService;
import service.VehicleService;

import java.math.BigDecimal;

/**
 * @author pradipta.sarma
 * @since 04/04/22
 */
public class DynamicPricingServiceImpl implements PricingService {
    private final VehicleService vehicleService;

    public DynamicPricingServiceImpl(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @Override
    public BigDecimal computePrice(Vehicle selectedVehicle, int startTime, int endTime, Branch branch) {
        long totalCount = branch.getVehicles().stream().count();
        long availableCount = branch.getVehicles().stream()
                .filter(vehicle -> vehicleService.isAvailable(selectedVehicle, startTime, endTime))
                .count();
        BigDecimal multiplier = BigDecimal.valueOf(1.0);

        if (totalCount != 0 && (availableCount/totalCount) <= .2) {
            multiplier = BigDecimal.valueOf(1.10);
        }

        return multiplier.multiply(BigDecimal.valueOf(selectedVehicle.getPricePerSlot() * (endTime - startTime)));
    }
}
