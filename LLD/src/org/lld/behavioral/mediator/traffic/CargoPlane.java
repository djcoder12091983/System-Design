package org.lld.behavioral.mediator.traffic;

// These represent the different entities operating inside our system.
// implementation - 2
public class CargoPlane extends Flight {
    public CargoPlane(AirTrafficControlTower atcTower, String flightNumber) {
        super(atcTower, flightNumber);
    }

    @Override
    public void land() {
        System.out.println("\n[Cargo " + flightNumber + "] Low fuel profile alert. Requesting priority landing...");
        atcTower.requestLanding(this);
    }

    @Override
    public void receiveAlert(String message) {
        System.out.println("[Cargo " + flightNumber + " Radio] Acknowledged message from ATC -> " + message);
    }
}