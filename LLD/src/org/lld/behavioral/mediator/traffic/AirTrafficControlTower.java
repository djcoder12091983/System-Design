package org.lld.behavioral.mediator.traffic;

// : Pilots only communicate directly with the ATC Tower (The Mediator).
// The tower intercepts the requests, calculates flight sequencing, and tells individual airplanes
// when to stay in a holding pattern or when to land.

public interface AirTrafficControlTower {
    void registerFlight(Flight flight);
    void requestLanding(Flight flight);
    void sendMessage(String message, Flight sender);
}