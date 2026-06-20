package org.lld.behavioral;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.lld.DPTestSuit;
import org.lld.behavioral.mediator.traffic.CargoPlane;
import org.lld.behavioral.mediator.traffic.CommercialAirliner;
import org.lld.behavioral.mediator.traffic.Flight;
import org.lld.behavioral.mediator.traffic.RunwayControlTower;

import static org.junit.jupiter.api.Assertions.assertTrue;

// These tests ensure that components communicate exclusively through the centralized mediator,
// validating coordination workflows and broadcast logic.
@DisplayName("Mediator Pattern - Air Traffic Control Coordination Test Suite")
public class AirTrafficMediatorTest extends DPTestSuit {

    private RunwayControlTower atcTower;
    private Flight commercialAirliner;
    private Flight cargoPlane;

    @BeforeEach
    void setupAirportSpace() {
        atcTower = new RunwayControlTower();
        commercialAirliner = new CommercialAirliner(atcTower, "AI-302");
        cargoPlane = new CargoPlane(atcTower, "DHL-99X");

        atcTower.registerFlight(commercialAirliner);
        atcTower.registerFlight(cargoPlane);
    }

    @Test
    @DisplayName("The Mediator must approve landing requests when the tracking resource is open")
    void testMediatorGrantsAuthorizationOnClearRunway() {
        commercialAirliner.land();

        String out = outputStreamCaptor.toString();
        assertTrue(out.contains(">>> [ATC TOWER] Runway CLEAR. Authorization APPROVED for AI-302"),
                "Mediator failed to authorize access to an open resource channel");
        assertTrue(out.contains("[Cargo DHL-99X Radio] Acknowledged message from ATC"),
                "Mediator failed to broadcast resource alert warning to non-sender colleague objects");
    }

    @Test
    @DisplayName("The Mediator must block landing requests and force objects to wait when a resource is busy")
    void testMediatorDeniesAccessOnOccupiedRunway() {
        // Air India occupies the runway
        commercialAirliner.land();
        outputStreamCaptor.reset();

        // DHL attempts to land simultaneously
        cargoPlane.land();

        String out = outputStreamCaptor.toString();
        assertTrue(out.contains(">>> [ATC TOWER] Runway BLOCKED! Authorization DENIED. DHL-99X"),
                "Safety Violation: Mediator permitted simultaneous conflicting access to resource tracking flags");
        assertTrue(out.contains("Please orbit airspace altitude level 3000 until cleared."),
                "Mediator failed to feed state mitigation parameters back down to denied colleague");
    }
}
