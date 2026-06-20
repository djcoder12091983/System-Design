package org.lld.creational.factory.logistic;

public class AirLogistics extends Logistics {
    @Override
    public Transport createTransport() {
        return new Plane(); // Returns an air product
    }
}
