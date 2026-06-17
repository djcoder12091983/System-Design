package org.lld.creational.factory.order;

import org.lld.creational.factory.order.india.IndiaCheckoutFactory;
import org.lld.creational.factory.order.us.USCheckoutFactory;

enum Region { US, INDIA } // dummy data

// some dummy testing logic
// TODO we need to setup proper test cases
public class Main {

    // A Simple Factory method using enum switch to deliver the Abstract Factory
    public static RegionalCheckoutFactory getFactory(Region region) {
        return switch (region) {
            case US -> new USCheckoutFactory();
            case INDIA -> new IndiaCheckoutFactory();
        };
    }

    // testing
    public static void main(String[] args) {
        double orderAmount = 100.0;

        // 1. Process for US region
        RegionalCheckoutFactory usFactory = getFactory(Region.US);
        OrderProcessor usProcessor = new OrderProcessor(usFactory);
        usProcessor.processOrder(orderAmount);

        // 2. Process for India region
        RegionalCheckoutFactory indiaFactory = getFactory(Region.INDIA);
        OrderProcessor indiaProcessor = new OrderProcessor(indiaFactory);
        indiaProcessor.processOrder(orderAmount);
    }
}
