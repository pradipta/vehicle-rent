package service;

import model.Branch;
import model.Vehicle;
import model.VehicleType;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * @author pradipta.sarma
 * @since 31/03/22
 */
public interface BranchService {
    Map<String, Branch> getAll();
    Optional<Branch> add(String name, VehicleType type) throws Exception;
    Optional<Branch> get(String name);

    List<Vehicle> getVehiclesOfType(Branch branch, VehicleType vehicleType);
}
