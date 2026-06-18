package org.lld.behavioral.strategy.pricing;

// Strategy C: Severe stock liquidation clearance run
public class ClearanceFlashSale implements PricingStrategy {
    @Override
    public double calculateFinalPrice(double rawCartTotal) {
        System.out.println("[Strategy: Clearance] Applying massive 60% clearance inventory reduction.");
        return rawCartTotal * 0.40; // Slice off 60%
    }
}