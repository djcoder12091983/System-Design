package org.lld.creational.factory.order.us;

import org.lld.creational.factory.order.InvoiceFormatter;

// US based invoice formatter
public class USInvoiceFormatter implements InvoiceFormatter {

    @Override
    public String formatInvoice(double amount, double tax) {
        double totalAmount = amount + tax;
        return String.format("Invoice (US): Amount: $%.2f, Tax: $%.2f, Total: $%.2f", amount, tax, totalAmount);
    }
}
