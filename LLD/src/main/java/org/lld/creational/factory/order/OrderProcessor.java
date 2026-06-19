package org.lld.creational.factory.order;

// order processing logic based on country based checkout logic
public class OrderProcessor {
    private final TaxCalculator taxCalculator;
    private final InvoiceFormatter invoiceFormatter;

    // The client only knows about the abstract interfaces and the factory, not the concrete implementations
    public OrderProcessor(RegionalCheckoutFactory checkoutFactory) {
        this.taxCalculator = checkoutFactory.getTaxCalculator();
        this.invoiceFormatter = checkoutFactory.getInvoiceFormatter();
    }

    // country based processing logic
    public void processOrder(double amount) {
        double tax = taxCalculator.calculateTax(amount);
        String invoice = invoiceFormatter.formatInvoice(amount, tax);
        System.out.println(invoice);
    }
}
