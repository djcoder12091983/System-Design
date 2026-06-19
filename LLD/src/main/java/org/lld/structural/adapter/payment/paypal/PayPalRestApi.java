package org.lld.structural.adapter.payment.paypal;

// Adaptee B: PayPal SDK (Expects an explicit currency code string and custom payload)
public class PayPalRestApi {
    public void submitPayment(double amount, String currency, String customEmail) {
        System.out.println("[PayPal API] Dispatched " + currency + " " + amount + " transfer request for: " + customEmail);
    }
}