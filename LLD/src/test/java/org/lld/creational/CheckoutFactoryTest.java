package org.lld.creational;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.lld.DPTestSuit;
import org.lld.creational.factory.order.InvoiceFormatter;
import org.lld.creational.factory.order.OrderProcessor;
import org.lld.creational.factory.order.RegionalCheckoutFactory;
import org.lld.creational.factory.order.TaxCalculator;
import org.lld.creational.factory.order.india.IndiaCheckoutFactory;
import org.lld.creational.factory.order.india.IndiaInvoiceFormatter;
import org.lld.creational.factory.order.india.IndiaTaxCalculator;
import org.lld.creational.factory.order.us.USCheckoutFactory;
import org.lld.creational.factory.order.us.USInvoiceFormatter;
import org.lld.creational.factory.order.us.USTaxCalculator;

import static org.junit.jupiter.api.Assertions.*;

// detailed test cases for checkout abstract factory
public class CheckoutFactoryTest extends DPTestSuit {
    // These tests ensure that a concrete factory cannot cross-contaminate object families.
    // The US factory must build US components, and the India factory must build India components.

    @Test
    @DisplayName("USCheckoutFactory must strictly generate US-compliant product families")
    void testUSFactoryProductFamilyCompliance() {
        RegionalCheckoutFactory usFactory = new USCheckoutFactory();

        TaxCalculator taxCalc = usFactory.getTaxCalculator();
        InvoiceFormatter invoiceForm = usFactory.getInvoiceFormatter();

        // Assert structural integrity
        assertNotNull(taxCalc);
        assertNotNull(invoiceForm);

        // Assert precise concrete type compliance
        assertInstanceOf(USTaxCalculator.class, taxCalc, "US Factory must manufacture a USTaxCalculator");
        assertInstanceOf(USInvoiceFormatter.class, invoiceForm, "US Factory must manufacture a USInvoiceFormatter");

        // Assert mathematical algorithm isolation (8% Sales Tax)
        assertEquals(8.0, taxCalc.calculateTax(100.0), 0.001, "US tax math must calculate exactly 8%");
    }

    @Test
    @DisplayName("IndiaCheckoutFactory must strictly generate India-compliant product families")
    void testIndiaFactoryProductFamilyCompliance() {
        RegionalCheckoutFactory indiaFactory = new IndiaCheckoutFactory();

        TaxCalculator taxCalc = indiaFactory.getTaxCalculator();
        InvoiceFormatter invoiceForm = indiaFactory.getInvoiceFormatter();

        assertNotNull(taxCalc);
        assertNotNull(invoiceForm);

        assertInstanceOf(IndiaTaxCalculator.class, taxCalc, "India Factory must manufacture an IndiaTaxCalculator");
        assertInstanceOf(IndiaInvoiceFormatter.class, invoiceForm, "India Factory must manufacture an IndiaInvoiceFormatter");

        // Assert mathematical algorithm isolation (18% GST)
        assertEquals(18.0, taxCalc.calculateTax(100.0), 0.001, "India tax math must calculate exactly 18%");
    }

    @Test
    @DisplayName("OrderProcessor should seamlessly process an order using the US ecosystem strategy")
    void testOrderProcessorWithUSFactory() {
        // Instantiate the client by injecting the US concrete factory graph
        RegionalCheckoutFactory usFactory = new USCheckoutFactory();
        OrderProcessor processor = new OrderProcessor(usFactory);

        processor.processOrder(100.0);

        String consoleOutput = outputStreamCaptor.toString().trim();

        // Verify the entire localized rendering layout
        assertTrue(consoleOutput.contains("Invoice (US)"), "Receipt layout must match US formatting templates");
        assertTrue(consoleOutput.contains("$108.00"), "Grand total must capture base amount + 8% tax");
        assertTrue(consoleOutput.contains("Tax: $8.00"), "Tax line breakdown must show exactly $8.00");
    }

    @Test
    @DisplayName("OrderProcessor should seamlessly process an order using the India ecosystem strategy")
    void testOrderProcessorWithIndiaFactory() {
        RegionalCheckoutFactory indiaFactory = new IndiaCheckoutFactory();
        OrderProcessor processor = new OrderProcessor(indiaFactory);

        processor.processOrder(100.0);

        String consoleOutput = outputStreamCaptor.toString().trim();

        assertTrue(consoleOutput.contains("Invoice (India)"), "Receipt layout must match India GST formatting templates");
        assertTrue(consoleOutput.contains("118.00"), "Grand total must capture base amount + 18% GST");
        assertTrue(consoleOutput.contains("Tax: 18.00"), "Tax line breakdown must show exactly ₹18.00");
    }
}
