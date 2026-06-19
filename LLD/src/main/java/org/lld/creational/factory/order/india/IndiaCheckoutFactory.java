package org.lld.creational.factory.order.india;

import org.lld.creational.factory.order.InvoiceFormatter;
import org.lld.creational.factory.order.RegionalCheckoutFactory;
import org.lld.creational.factory.order.TaxCalculator;

// india based checkout factory
public class IndiaCheckoutFactory implements RegionalCheckoutFactory {

    @Override
    public TaxCalculator getTaxCalculator() {
        return new IndiaTaxCalculator();
    }

    @Override
    public InvoiceFormatter getInvoiceFormatter() {
        return new IndiaInvoiceFormatter();
    }
}