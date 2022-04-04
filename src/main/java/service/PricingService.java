package service;

import model.Branch;
import model.Vehicle;

import java.math.BigDecimal;

/**
 * @author pradipta.sarma
 * @since 04/04/22
 */
public interface PricingService {
    BigDecimal computePrice(Vehicle selectedVehicle, int startTime, int endTime, Branch branch);
}
