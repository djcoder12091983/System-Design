package org.lld.behavioral.strategy.pricing;

// Strategy A: No discount promotions active
public class RegularPricing implements PricingStrategy {
    @Override
    public double calculateFinalPrice(double rawCartTotal) {
        System.out.println("[Strategy: Standard] Retaining full base price format.");
        return rawCartTotal;
    }
}
