package org.lld.behavioral.mediator.traffic;

// dummy test class
// TODO: Add actual test cases
public class Main {
    public static void main(String[] args) {
        // 1. Initialize the central mediator
        RunwayControlTower atcBase = new RunwayControlTower();

        // 2. Setup the interacting components
        Flight airIndia = new CommercialAirliner(atcBase, "AI-302");
        Flight emirates = new CommercialAirliner(atcBase, "EK-511");
        Flight dhlCargo = new CargoPlane(atcBase, "DHL-99X");

        // 3. Register components inside the mediator hub matrix
        atcBase.registerFlight(airIndia);
        atcBase.registerFlight(emirates);
        atcBase.registerFlight(dhlCargo);

        // --- Execution Flow Simulation ---

        // Air India requests landing first
        airIndia.land();

        // Emirates attempts to land while Air India is occupying the runway track
        emirates.land();

        // Clear the track
        atcBase.clearRunway();

        // DHL Cargo takes the open slot
        dhlCargo.land();
    }
}