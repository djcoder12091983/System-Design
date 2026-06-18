package org.lld.behavioral.mediator.traffic;

// These represent the different entities operating inside our system.
// implementation - 1
public class CommercialAirliner extends Flight {
    public CommercialAirliner(AirTrafficControlTower atcTower, String flightNumber) {
        super(atcTower, flightNumber);
    }

    @Override
    public void land() {
        System.out.println("\n[Flight " + flightNumber + "] Requesting landing authorization clearance...");
        atcTower.requestLanding(this);
    }

    @Override
    public void receiveAlert(String message) {
        System.out.println("[Flight " + flightNumber + " Radio] Received update from ATC -> " + message);
    }
}