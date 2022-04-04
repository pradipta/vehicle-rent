package model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author pradipta.sarma
 * @since 30/03/22
 */

@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Branch {
    //Assuming name to be unique as the problem statement gets branches by name
    @EqualsAndHashCode.Include
    private String name;
    private Set<VehicleType> supportedVehicleTypes;
    private Set<Vehicle> vehicles;

    public boolean addVehicle(Vehicle vehicle) {
        return vehicles.add(vehicle);
    }
}
