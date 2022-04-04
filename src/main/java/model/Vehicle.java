package model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * @author pradipta.sarma
 * @since 30/03/22
 */

@Data
@Builder
@EqualsAndHashCode
public class Vehicle {
    private String id;
    private VehicleType vehicleType;
    private Map<Integer, Boolean> availabilityMap;
}
