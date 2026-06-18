package org.lld.behavioral.strategy.pricing;

// Driver class to test the pricing strategy pattern implementation
// TODO come up with a better test suite using test framework
public class Main {
    public static void main(String[] args) {
        double customerCartAmount = 5000.00;

        // 1. Initialize the checkout system with a baseline default strategy profile
        CheckoutContext cashRegister = new CheckoutContext(new RegularPricing());

        // Scenario A: Standard business day transaction run
        System.out.println("=== RUN 1: MARCH REGULAR MARKET DAY ===");
        cashRegister.executeCheckout(customerCartAmount);

        // Scenario B: Season flips to December. The client mutates the strategy type dynamically.
        System.out.println("=== RUN 2: DECEMBER HOLIDAY SALES WEEK ===");
        cashRegister.setPricingStrategy(new ChristmasDiscount());
        cashRegister.executeCheckout(customerCartAmount);

        // Scenario C: Sudden stock clearing operational fire-sale triggered
        System.out.println("=== RUN 3: MIDNIGHT INVENTORY FLASH SALE ===");
        cashRegister.setPricingStrategy(new ClearanceFlashSale());
        cashRegister.executeCheckout(customerCartAmount);
    }
}
