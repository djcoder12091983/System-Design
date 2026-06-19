package org.lld.behavioral.mediator.traffic;

import java.util.ArrayList;
import java.util.List;

// This central registry holds references
// to all components and contains the orchestration algorithms to coordinate safe execution workflows.
public class RunwayControlTower implements AirTrafficControlTower {
    private final List<Flight> registeredFlights = new ArrayList<>();
    private boolean isRunwayOccupied = false;

    @Override
    public void registerFlight(Flight flight) {
        registeredFlights.add(flight);
    }

    @Override
    public void requestLanding(Flight flight) {
        if (!isRunwayOccupied) {
            System.out.println(">>> [ATC TOWER] Runway CLEAR. Authorization APPROVED for " + flight.getFlightNumber());
            isRunwayOccupied = true;

            // Broadcast warning to all OTHER flights in the vicinity zone
            sendMessage("Runway is now locked for incoming landing structural operations.", flight);
        } else {
            System.out.println(">>> [ATC TOWER] Runway BLOCKED! Authorization DENIED. " + flight.getFlightNumber() + " must enter holding pattern.");
            flight.receiveAlert("Please orbit airspace altitude level 3000 until cleared.");
        }
    }

    @Override
    public void sendMessage(String message, Flight sender) {
        // Intercept message routing and push it out exclusively to target recipients
        for (Flight flight : registeredFlights) {
            if (flight != sender) { // Do not send back to the initiator
                flight.receiveAlert(message);
            }
        }
    }

    // Management helper to reset internal system variables
    public void clearRunway() {
        System.out.println("\n>>> [ATC TOWER] Previous aircraft cleared runway. Runway state: OPEN.");
        this.isRunwayOccupied = false;
    }
}
