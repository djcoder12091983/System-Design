package org.lld.creational.factory.logistic;

public class SeaLogistics extends Logistics {
    @Override
    public Transport createTransport() {
        return new Ship(); // Returns a sea product
    }
}