package org.lld.creational.factory.logistic;

public class Plane implements Transport {
    @Override
    public void deliver() {
        System.out.println("Delivering urgent envelope by air via a cargo plane.");
    }
}
