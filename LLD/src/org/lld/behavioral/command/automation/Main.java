package org.lld.behavioral.command.automation;

// dummy test class
// TODO: Add actual test cases
public class Main {
    public static void main(String[] args) {
        // 1. Instantiate the physical appliances (Receivers)
        SmartLight livingRoomLight = new SmartLight("Living Room");
        AirConditioner livingRoomAC = new AirConditioner();

        // 2. Instantiate the Remote Controller (Invoker)
        RemoteControl remote = new RemoteControl();

        System.out.println("=== EXECUTING ACTIONS ===");

        // Action A: Turn on the living room light
        remote.setCommand(new TurnOnLightCommand(livingRoomLight));
        remote.pressButton();

        // Action B: Set AC temperature to 18°C
        remote.setCommand(new SetTemperatureCommand(livingRoomAC, 18));
        remote.pressButton();

        // Action C: Turn up AC temperature to 22°C
        remote.setCommand(new SetTemperatureCommand(livingRoomAC, 22));
        remote.pressButton();

        System.out.println("\n=== TESTING THE UNDO ENGINE ===");

        // Undo 1: Reverts Action C (Brings AC back from 22°C to 18°C)
        remote.pressUndoButton();

        // Undo 2: Reverts Action B (Brings AC back from 18°C to its default 24°C)
        remote.pressUndoButton();

        // Undo 3: Reverts Action A (Turns off the living room light)
        remote.pressUndoButton();
    }
}
