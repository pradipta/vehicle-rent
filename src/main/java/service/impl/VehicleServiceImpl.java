package service.impl;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import model.Branch;
import model.Vehicle;
import model.VehicleType;
import service.BranchService;
import service.VehicleService;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static model.Vehicle.currentCentreId;

/**
 * @author pradipta.sarma
 * @since 31/03/22
 */
@AllArgsConstructor
public class VehicleServiceImpl implements VehicleService {
    private Set<Vehicle> vehicles = new HashSet<>();
    private BranchService branchService;

    @Override
    public Set<Vehicle> getAll() {
        return this.vehicles;
    }

    @Override
    public Optional<Vehicle> add(String branchName, VehicleType vehicleType, Double pricePerSlot) throws Exception {
        Branch branch = branchService.get(branchName).orElseThrow(() -> new Exception("Branch not found"));

        if (!branch.getSupportedVehicleTypes().contains(vehicleType)) {
            System.out.println(false);
            throw new Exception("Vehicle type not suported");
        }

        Vehicle vehicle = Vehicle.builder()
                .pricePerSlot(pricePerSlot)
                .vehicleType(vehicleType)
                .id("V"+currentCentreId)
                .availabilityMap(new HashMap<>())
                .build();

        currentCentreId += 1;

        if (!branch.addVehicle(vehicle)) {
            return Optional.empty();
        }
        System.out.println(true);
        return Optional.of(vehicle);
    }

    @Override
    public List<Vehicle> getVehiclesByBranchAndTime(String branchName, int startTime, int endTime) throws Exception {
        Branch branch = branchService.get(branchName).orElseThrow(() -> new Exception("Branch not found"));
        return branch.getVehicles().stream()
                .filter(vehicle -> isAvailable(vehicle, startTime, endTime))
                .sorted((vehicle1, vehicle2) -> vehicle1.getPricePerSlot().compareTo(vehicle2.getPricePerSlot()))
                .toList();
    }

    @Override
    public Boolean isAvailable(Vehicle vehicle, int startTime, int endTime) {
        for (int i = startTime; i <= endTime; i++) {
            if (vehicle.getAvailabilityMap().containsKey(i) && !vehicle.getAvailabilityMap().get(i)) {
                return false;
            }
        }
        return true;
    }
}
