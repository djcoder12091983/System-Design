package org.lld.behavioral.strategy.pricing;

// The context maintains the reference link pointing to the specific strategy instance.
// Crucially, it has no structural knowledge of how the calculations work;
// it simply executes the interface method.

public class CheckoutContext {
    // Composition: Aggregating the interchangeable strategy abstraction interface
    private PricingStrategy pricingStrategy;

    // The strategy configuration can be established during initialization...
    public CheckoutContext(PricingStrategy strategy) {
        this.pricingStrategy = strategy;
    }

    // ...or mutated dynamically at runtime using a setter method
    public void setPricingStrategy(PricingStrategy strategy) {
        this.pricingStrategy = strategy;
    }

    public void executeCheckout(double rawTotal) {
        System.out.println("Processing transaction basket. Starting Subtotal: ₹" + rawTotal);

        // Delegating calculations down to the active wrapped strategy target implementation
        double finalTotal = pricingStrategy.calculateFinalPrice(rawTotal);

        System.out.println("Payment authorization finalized. Settlement Total: ₹" + finalTotal + "\n");
    }
}