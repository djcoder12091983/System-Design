package org.lld.creational.factory.order;

// region based invoice formatter
public interface InvoiceFormatter {
    String formatInvoice(double amount, double tax);
}
