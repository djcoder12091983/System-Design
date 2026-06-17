package org.lld.creational.factory.order.india;

import org.lld.creational.factory.order.InvoiceFormatter;

// india based invoice formatter
public class IndiaInvoiceFormatter implements InvoiceFormatter {

    @Override
    public String formatInvoice(double amount, double tax) {
        double totalAmount = amount + tax;
        return String.format("Invoice (India): Amount: %.2f, Tax: %.2f, Total: %.2f", amount, tax, totalAmount);
    }
}
