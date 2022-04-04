package service.impl;

import model.Branch;
import model.Vehicle;
import model.VehicleType;
import service.BranchService;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author pradipta.sarma
 * @since 31/03/22
 */
public class BranchServiceImpl implements BranchService {
    private Map<String, Branch> branches = new HashMap<>();

    @Override
    public Map<String, Branch> getAll() {
        return branches;
    }

    @Override
    public Optional<Branch> add(String name, Set<VehicleType> vehicleTypes) throws Exception {
        Branch branch = Branch.builder()
                .name(name)
                .vehicles(new HashSet<>())
                .supportedVehicleTypes(vehicleTypes)
                .build();

        if (branches.containsKey(name)) {
            throw new Exception(String.format("Branch: %s already exists", name));
        }

        branches.put(name, branch);
        return Optional.of(branch);
    }

    @Override
    public Optional<Branch> get(String name) {
        return Optional.ofNullable(branches.get(name));
    }

    @Override
    public List<Vehicle> getVehiclesOfType(Branch branch, VehicleType vehicleType) {
        return branch.getVehicles().stream()
                .filter(vehicle -> vehicle.getVehicleType().equals(vehicleType))
                .collect(Collectors.toList());
    }
}
