package org.lld.structural.adapter.payment.apple;

import org.lld.structural.adapter.payment.PaymentProcessor;

// Adapter 3: Apple Pay Adapter
public class ApplePayAdapter implements PaymentProcessor {
    private final ApplePayGateway applePayGateway;

    public ApplePayAdapter(ApplePayGateway applePayGateway) {
        this.applePayGateway = applePayGateway;
    }

    @Override
    public void processPayment(String userEmail, double amountInDollars) {
        // Translation logic: Extract systemic configuration values
        String merchantSessionId = "MERCHANT_ID_ZONE_" + userEmail.substring(userEmail.indexOf("@") + 1).toUpperCase();

        applePayGateway.tokenizedTransaction(merchantSessionId, amountInDollars);
    }
}