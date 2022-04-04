package factory;

import service.PricingService;
import service.VehicleService;
import service.impl.DynamicPricingServiceImpl;
import service.impl.StaticPricingServiceImpl;

import java.util.Map;

/**
 * @author pradipta.sarma
 * @since 04/04/22
 */
public class PricingServiceFactory {
    private final Map<String, PricingService> pricingServiceMap;
    private final VehicleService vehicleService;

    public PricingServiceFactory(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
        this.pricingServiceMap = Map.of("STATIC", new StaticPricingServiceImpl(),
                "DYNAMIC", new DynamicPricingServiceImpl(this.vehicleService));
    }

    public PricingService get(String strategy) throws Exception {
        if (!pricingServiceMap.containsKey(strategy)) {
            throw new Exception("Invalid pricing strategy");
        }
        return pricingServiceMap.get(strategy);
    }
}
