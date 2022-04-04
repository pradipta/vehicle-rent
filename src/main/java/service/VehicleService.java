package service;

import model.Vehicle;
import model.VehicleType;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author pradipta.sarma
 * @since 31/03/22
 */
public interface VehicleService {
    Set<Vehicle> getAll();
    Optional<Vehicle> add(String branchName, VehicleType vehicleType, Double pricePerSlot) throws Exception;
    List<Vehicle> getVehiclesByBranchAndTime(String branchName, int i, int i1) throws Exception;
    Boolean isAvailable(Vehicle vehicle, int startTime, int endTime);
}
