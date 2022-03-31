package service.impl;

import model.Branch;
import model.Vehicle;
import model.VehicleType;
import service.BranchService;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * @author pradipta.sarma
 * @since 31/03/22
 */
public class BranchServiceImpl implements BranchService {
    private Set<Branch> branches = new HashSet<>();

    @Override
    public Set<Branch> getAll() {
        return branches;
    }

    @Override
    public Optional<Branch> add(String name, VehicleType type) throws Exception {
        Vehicle vehicle = Vehicle.builder()
                .id(UUID.randomUUID().toString())
                .vehicleType(type)
                .build();

        Branch branch = Branch.builder()
                .name(name)
                .vehicles(Set.of(vehicle))
                .build();

        if (branches.contains(branch)) {
            //We could do this by a getByName() [since name is already unique] but since we are using a set, we do it this way for now
            throw new Exception(String.format("Branch: %s already exists", name));
        }

        branches.add(branch);
        return Optional.of(branch);
    }

    @Override
    public Optional<Branch> get(String name) {
        return branches.stream().filter(branch -> branch.getName().equals(name)).findFirst();
    }
}
