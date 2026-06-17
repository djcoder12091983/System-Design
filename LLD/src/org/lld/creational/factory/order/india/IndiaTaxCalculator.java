package org.lld.creational.factory.order.india;

import org.lld.creational.factory.order.TaxCalculator;

// india based tax calculator
public class IndiaTaxCalculator implements TaxCalculator {

    private static final double GST_RATE = 0.18; // 18% GST

    @Override
    public double calculateTax(double amount) {
        return amount * GST_RATE;
    }
}
