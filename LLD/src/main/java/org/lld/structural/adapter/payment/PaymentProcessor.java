package org.lld.structural.adapter.payment;

// trying to solve payment gateway to integrate 3rd party payment processors
public interface PaymentProcessor {
    // common adapter methods to handle multiple payment processors
    void processPayment(String userEmail, double amountInDollars);
}
