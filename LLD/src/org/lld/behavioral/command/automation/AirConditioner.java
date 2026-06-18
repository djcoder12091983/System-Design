package org.lld.behavioral.command.automation;

// Receiver 2
public class AirConditioner {
    private int temperature = 24; // Default Celsius

    public void setTemperature(int temp) {
        this.temperature = temp;
        System.out.println("[Device] AC temperature set to " + temperature + "°C.");
    }

    public int getTemperature() { return temperature; }
}
