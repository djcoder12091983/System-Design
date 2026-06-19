package org.lld.behavioral.mediator.traffic;

// Components that interact with each other are called "Colleagues."
// They only communicate with the mediator, never directly with other colleague classes.
public abstract class Flight {
    // The link back to the central mediator
    protected final AirTrafficControlTower atcTower;
    protected final String flightNumber;

    protected Flight(AirTrafficControlTower atcTower, String flightNumber) {
        this.atcTower = atcTower;
        this.flightNumber = flightNumber;
    }

    public String getFlightNumber() { return flightNumber; }

    // Common actions delegated down to the mediator
    public abstract void land();
    public abstract void receiveAlert(String message);
}