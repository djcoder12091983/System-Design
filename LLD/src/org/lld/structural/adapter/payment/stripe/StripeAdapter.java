package org.lld.structural.adapter.payment.stripe;

import org.lld.structural.adapter.payment.PaymentProcessor;

// Adapter 1: Stripe Adapter
public class StripeAdapter implements PaymentProcessor {
    private final StripePaymentSdk stripeSdk;

    public StripeAdapter(StripePaymentSdk stripeSdk) {
        this.stripeSdk = stripeSdk;
    }

    @Override
    public void processPayment(String userEmail, double amountInDollars) {
        // Translation logic: Dollars to cents
        long cents = Math.round(amountInDollars * 100);
        String mockToken = "tok_" + userEmail.hashCode();

        stripeSdk.executeCharge(cents, mockToken);
    }
}
