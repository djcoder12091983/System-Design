package org.lld.creational.factory.logistic;

// TODO this is dummy test class in this class itself we have taken care of factory method
// TODO we need to setup proper test cases and this factory method we need to get out of this main dummy test class
public class Main {

    // A helper method to simulate selecting the right logistics system based on input
    private static Logistics configureLogistics(ShippingRoute route) {
        return switch (route) {
            case LAND -> new RoadLogistics();
            case SEA  -> new SeaLogistics();
            case AIR  -> new AirLogistics();
        };
    }

    public static void main(String[] args) {
        // Example 1: Customer chooses standard domestic shipping
        Logistics roadLogistics = configureLogistics(ShippingRoute.LAND);
        roadLogistics.planDelivery();

        // Example 2: Customer chooses international oceanic shipping
        Logistics seaLogistics = configureLogistics(ShippingRoute.SEA);
        seaLogistics.planDelivery();

        // Example 3: Customer pays for next-day express delivery
        Logistics airLogistics = configureLogistics(ShippingRoute.AIR);
        airLogistics.planDelivery();
    }
}
