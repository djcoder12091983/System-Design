package org.lld.creational.factory.order.us;

import org.lld.creational.factory.order.InvoiceFormatter;
import org.lld.creational.factory.order.RegionalCheckoutFactory;
import org.lld.creational.factory.order.TaxCalculator;

// US based checkout factory
public class USCheckoutFactory implements RegionalCheckoutFactory {

    @Override
    public TaxCalculator getTaxCalculator() {
        return new USTaxCalculator();
    }

    @Override
    public InvoiceFormatter getInvoiceFormatter() {
        return new USInvoiceFormatter();
    }
}
