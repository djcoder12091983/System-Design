package org.lld.creational.factory.logistic;

public abstract class Logistics {

    // The core business logic that stays identical across all logistics types
    public void planDelivery() {
        System.out.println("\n--- Initiating Logistics Planning ---");
        System.out.println("Calculating optimal route...");

        // Call the factory method to get a product without knowing its exact class
        Transport transport = createTransport();

        // Use the product
        transport.deliver();
    }

    // This is the actual Factory Method
    public abstract Transport createTransport();
}
