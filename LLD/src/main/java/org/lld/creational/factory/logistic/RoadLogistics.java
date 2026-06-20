package org.lld.creational.factory.logistic;

public class RoadLogistics extends Logistics {
    @Override
    public Transport createTransport() {
        return new Truck(); // Returns a land product
    }
}