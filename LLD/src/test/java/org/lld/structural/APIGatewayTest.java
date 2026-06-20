package org.lld.structural;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.lld.DPTestSuit;
import org.lld.structural.adapter.payment.PaymentGatewayType;
import org.lld.structural.adapter.payment.PaymentProcessor;
import org.lld.structural.adapter.payment.apple.ApplePayAdapter;
import org.lld.structural.adapter.payment.paypal.PayPalAdapter;
import org.lld.structural.adapter.payment.paypal.PayPalRestApi;
import org.lld.structural.adapter.payment.router.PaymentProcessorFactory;
import org.lld.structural.adapter.payment.stripe.StripeAdapter;
import org.lld.structural.adapter.payment.stripe.StripePaymentSdk;

import static org.junit.jupiter.api.Assertions.*;

// These tests ensure that our adapters convert data formats cleanly (e.g., dollars to cents for Stripe)
// and that the core system remains decoupled from vendor SDK variations.
@DisplayName("Adapter Pattern - Payment Gateway Engine Test Suite")
public class APIGatewayTest extends DPTestSuit {
    @Test
    @DisplayName("StripeAdapter should accurately translate dollar doubles into long cents data primitives")
    void testStripeAdapterDataTransformation() {
        StripePaymentSdk mockStripeSdk = new StripePaymentSdk();
        PaymentProcessor stripeAdapter = new StripeAdapter(mockStripeSdk);

        // Core app interacts through dollars interface
        stripeAdapter.processPayment("test@user.com", 149.99);

        String consoleOutput = outputStreamCaptor.toString();
        // 149.99 dollars must translate precisely to 14999 cents
        assertTrue(consoleOutput.contains("charged 14999 cents"),
                "Adapter math data transformation failed to match structural cent parameters");
    }

    @Test
    @DisplayName("PayPalAdapter should pass raw strings directly matching API parameters")
    void testPayPalAdapterDataContractMapping() {
        PayPalRestApi mockPayPalApi = new PayPalRestApi();
        PaymentProcessor payPalAdapter = new PayPalAdapter(mockPayPalApi);

        payPalAdapter.processPayment("bob@company.com", 1250.00);

        String consoleOutput = outputStreamCaptor.toString();
        assertTrue(consoleOutput.contains("Dispatched USD 1250.0 transfer request"));
        assertTrue(consoleOutput.contains("for: bob@company.com"));
    }

    @Test
    @DisplayName("PaymentProcessorFactory must deliver correct adapter strategies based on enum profiles")
    void testFactoryAdapterRoutingIntegrity() {
        PaymentProcessor processor = PaymentProcessorFactory.getProcessor(PaymentGatewayType.APPLE_PAY);
        assertInstanceOf(ApplePayAdapter.class, processor, "Factory must route to a matching ApplePayAdapter template");

        assertThrows(IllegalArgumentException.class, () -> {
            PaymentProcessorFactory.getProcessor(null);
        }, "Factory should reject processing request if routing type token is missing");
    }
}
