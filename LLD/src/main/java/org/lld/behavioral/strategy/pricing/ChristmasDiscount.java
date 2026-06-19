package org.lld.behavioral.strategy.pricing;

// Strategy B: Holiday flat reduction rate formula
public class ChristmasDiscount implements PricingStrategy {
    @Override
    public double calculateFinalPrice(double rawCartTotal) {
        System.out.println("[Strategy: Holiday] Applying seasonal flat 20% discount markdown.");
        return rawCartTotal * 0.80; // Slice off 20%
    }
}