package service;

import model.Branch;
import model.VehicleType;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author pradipta.sarma
 * @since 31/03/22
 */
public interface BranchService {
    Set<Branch> getAll();
    Optional<Branch> add(String name, VehicleType type) throws Exception;
    Optional<Branch> get(String name);
}
