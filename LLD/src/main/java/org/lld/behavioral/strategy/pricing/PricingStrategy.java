package org.lld.behavioral.strategy.pricing;

// This defines the common contract that all variant algorithm modules must implement.
public interface PricingStrategy {
    // Calculates the final checkout balance total after evaluating promotions
    double calculateFinalPrice(double rawCartTotal);
}
