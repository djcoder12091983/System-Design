package org.lld.behavioral.command.automation;

// Concrete Command 2: Adjusting AC Temperature
public class SetTemperatureCommand implements SmartHomeCommand {
    private final AirConditioner ac;
    private final int newTemperature;
    private int previousTemperature; // Saved state for undo execution

    public SetTemperatureCommand(AirConditioner ac, int targetTemp) {
        this.ac = ac;
        this.newTemperature = targetTemp;
    }

    @Override
    public void execute() {
        previousTemperature = ac.getTemperature(); // Capture current state before changing
        ac.setTemperature(newTemperature);
    }

    @Override
    public void undo() {
        System.out.print("[Undo Action] Restoring AC state: ");
        ac.setTemperature(previousTemperature); // Revert to the snapshot state
    }
}
