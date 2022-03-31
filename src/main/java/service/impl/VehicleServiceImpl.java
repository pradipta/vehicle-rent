package service.impl;

import model.Vehicle;
import service.VehicleService;

import java.util.HashSet;
import java.util.Set;

/**
 * @author pradipta.sarma
 * @since 31/03/22
 */
public class VehicleServiceImpl implements VehicleService {
    private Set<Vehicle> vehicles = new HashSet<>();
}
