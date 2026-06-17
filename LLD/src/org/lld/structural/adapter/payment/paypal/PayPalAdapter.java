package org.lld.structural.adapter.payment.paypal;

import org.lld.structural.adapter.payment.PaymentProcessor;

// Adapter 2: PayPal Adapter
public class PayPalAdapter implements PaymentProcessor {
    private final PayPalRestApi payPalApi;

    public PayPalAdapter(PayPalRestApi payPalApi) {
        this.payPalApi = payPalApi;
    }

    @Override
    public void processPayment(String userEmail, double amountInDollars) {
        // Translation logic: Supply the implicit USD requirement
        payPalApi.submitPayment(amountInDollars, "USD", userEmail);
    }
}
