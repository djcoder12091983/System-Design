package org.lld.creational.factory.logistic;

public class AirLogistics extends Logistics {
    @Override
    protected Transport createTransport() {
        return new Plane(); // Returns an air product
    }
}
