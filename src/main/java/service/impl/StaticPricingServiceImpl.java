package service.impl;

import model.Branch;
import model.Vehicle;
import service.PricingService;

import java.math.BigDecimal;

/**
 * @author pradipta.sarma
 * @since 04/04/22
 */
public class StaticPricingServiceImpl implements PricingService {
    @Override
    public BigDecimal computePrice(Vehicle selectedVehicle, int startTime, int endTime, Branch branch) {
        return BigDecimal.valueOf(selectedVehicle.getPricePerSlot() * (endTime - startTime));
    }
}
