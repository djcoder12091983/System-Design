package org.lld.creational.factory.logistic;

public class SeaLogistics extends Logistics {
    @Override
    protected Transport createTransport() {
        return new Ship(); // Returns a sea product
    }
}