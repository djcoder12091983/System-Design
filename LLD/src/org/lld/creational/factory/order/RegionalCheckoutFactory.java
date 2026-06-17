package org.lld.creational.factory.order;

// checkput factory based on which we process order checkout
public interface RegionalCheckoutFactory {
    // region based details will be used to calculate tax and format invoice
    TaxCalculator getTaxCalculator();
    InvoiceFormatter getInvoiceFormatter();
}
