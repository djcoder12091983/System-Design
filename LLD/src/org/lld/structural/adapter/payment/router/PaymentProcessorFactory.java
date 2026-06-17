package org.lld.structural.adapter.payment.router;

import org.lld.structural.adapter.payment.PaymentGatewayType;
import org.lld.structural.adapter.payment.PaymentProcessor;
import org.lld.structural.adapter.payment.apple.ApplePayAdapter;
import org.lld.structural.adapter.payment.apple.ApplePayGateway;
import org.lld.structural.adapter.payment.paypal.PayPalAdapter;
import org.lld.structural.adapter.payment.paypal.PayPalRestApi;
import org.lld.structural.adapter.payment.stripe.StripeAdapter;
import org.lld.structural.adapter.payment.stripe.StripePaymentSdk;

// routing logic using factory
// TODO this class needs to be tested
public class PaymentProcessorFactory {

    public static PaymentProcessor getProcessor(PaymentGatewayType type) {
        if (type == null) {
            throw new IllegalArgumentException("Gateway type cannot be null");
        }

        // The factory handles instantiating the SDK and wrapping it in the correct adapter
        return switch (type) {
            case STRIPE -> new StripeAdapter(new StripePaymentSdk());
            case PAYPAL -> new PayPalAdapter(new PayPalRestApi());
            case APPLE_PAY -> new ApplePayAdapter(new ApplePayGateway());
        };
    }
}