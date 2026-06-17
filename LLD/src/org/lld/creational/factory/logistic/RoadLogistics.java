package org.lld.creational.factory.logistic;

public class RoadLogistics extends Logistics {
    @Override
    protected Transport createTransport() {
        return new Truck(); // Returns a land product
    }
}