package org.lld.structural.adapter.payment.apple;

// Adaptee C: Apple Pay SDK (Requires a secure merchant session identification string)
public class ApplePayGateway {
    public boolean tokenizedTransaction(String merchantSessionId, double rawVal) {
        System.out.println("[Apple Pay] Authorized merchant session " + merchantSessionId + " for $" + rawVal);
        return true;
    }
}
