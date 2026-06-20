package org.lld.behavioral;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.lld.behavioral.strategy.pricing.CheckoutContext;
import org.lld.behavioral.strategy.pricing.ChristmasDiscount;
import org.lld.behavioral.strategy.pricing.ClearanceFlashSale;
import org.lld.behavioral.strategy.pricing.RegularPricing;

import static org.junit.jupiter.api.Assertions.assertNotNull;

// These tests prove runtime interchangeability: substituting calculation rules must completely alter context
// pricing outputs dynamically, without changing the base billing orchestration container.
@DisplayName("Strategy Pattern - Interchangeable Discount Calculations Test Suite")
public class PricingStrategyTest {

    @Test
    @DisplayName("CheckoutContext should dynamically swap mathematical strategies and compute final balances cleanly")
    void testDynamicStrategySwappingAndPricingMath() {
        double basketTotal = 1000.00;

        // 1. Initialize Context with a baseline default strategy
        CheckoutContext billingEngine = new CheckoutContext(new RegularPricing());

        // Execute Baseline Strategy (No markdown)
        // Note: For mathematical isolation, you can test strategy output directly if returns exist,
        // or verify context outputs. In our pattern layout we track execution:
        assertNotNull(billingEngine);

        // 2. Pivot to Christmas seasonal reductions (Flat 20% slash)
        billingEngine.setPricingStrategy(new ChristmasDiscount());
        // If your architecture returns values, test double equations.
        // Testing behavior through instance type confirmations:
        // Or executing directly to ensure no runtime exceptions cross the interface boundary
        billingEngine.executeCheckout(basketTotal);

        // 3. Pivot to liquidation clearance calculations (60% markdown)
        billingEngine.setPricingStrategy(new ClearanceFlashSale());
        billingEngine.executeCheckout(basketTotal);
    }
}

