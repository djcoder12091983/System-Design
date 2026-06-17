package org.lld.creational.factory.logistic;

public class Ship implements Transport {
    @Override
    public void deliver() {
        System.out.println("Delivering cargo container by sea via a cargo ship.");
    }
}