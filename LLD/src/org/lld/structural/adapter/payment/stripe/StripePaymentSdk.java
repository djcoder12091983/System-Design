package org.lld.structural.adapter.payment.stripe;

// Adaptee A: Stripe SDK (Expects amounts in Cents as a Long)
public class StripePaymentSdk {
    public void executeCharge(long cents, String stripeTokenId) {
        System.out.println("[Stripe SDK] Successfully charged " + cents + " cents using token: " + stripeTokenId);
    }
}
