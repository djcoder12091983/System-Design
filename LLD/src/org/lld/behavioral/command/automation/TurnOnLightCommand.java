package org.lld.behavioral.command.automation;

// Concrete Command 1: Controlling Light Toggle
public class TurnOnLightCommand implements SmartHomeCommand {
    private final SmartLight light;

    public TurnOnLightCommand(SmartLight light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn();
    }

    @Override
    public void undo() {
        light.turnOff(); // Reversing turning it on means turning it off
    }
}