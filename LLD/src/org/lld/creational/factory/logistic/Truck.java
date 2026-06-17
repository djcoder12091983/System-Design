package org.lld.creational.factory.logistic;

public class Truck implements Transport {
    @Override
    public void deliver() {
        System.out.println("Delivering box by land in a temperature-controlled truck.");
    }
}
