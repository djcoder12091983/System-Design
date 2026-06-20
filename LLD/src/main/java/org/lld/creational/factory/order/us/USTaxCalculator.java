package org.lld.creational.factory.order.us;

import org.lld.creational.factory.order.TaxCalculator;

// US based tax calculator
public class USTaxCalculator implements TaxCalculator {

    private static final double SALES_TAX_RATE = 0.08; // 8% sales tax

    @Override
    public double calculateTax(double amount) {
        return amount * SALES_TAX_RATE;
    }
}
