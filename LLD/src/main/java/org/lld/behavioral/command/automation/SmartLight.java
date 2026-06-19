package org.lld.behavioral.command.automation;

// Receiver 1
public class SmartLight {
    private final String roomName;
    private boolean isOn = false;

    public SmartLight(String roomName) { this.roomName = roomName; }

    public void turnOn() {
        isOn = true;
        System.out.println("[Device] " + roomName + " Light is turned ON.");
    }

    public void turnOff() {
        isOn = false;
        System.out.println("[Device] " + roomName + " Light is turned OFF.");
    }
}