package org.lld.behavioral.interpreter.discount;

// dummy test class
// TODO: Add actual test cases

// In production, a Parser engine parses rule text into these objects. In this example, we build the syntax tree
// manually to demonstrate the evaluation flow.

public class Main {
    public static void main(String[] args) {
        // Rule: (cartTotal > 5000 AND isVip == true) OR item_count == 10

        // 1. Construct the syntax tree nodes
        Expression condition1 = new GreaterThanExpression("cartTotal", 5000.0);
        Expression condition2 = new EqualsExpression("isVip", true);

        // Group the first branch with an AND expression
        Expression vipDiscountBranch = new AndExpression(condition1, condition2);

        // Backup criteria branch
        Expression bulkOrderBranch = new EqualsExpression("itemCount", 10);

        // Group the final absolute rule tree using an OR statement
        Expression finalDiscountRule = new OrExpression(vipDiscountBranch, bulkOrderBranch);


        // 2. Test Case A: VIP customer buying high value items
        System.out.println("=== EVALUATING CART A ===");
        Context cartA = new Context();
        cartA.setParam("cartTotal", 6500.0);
        cartA.setParam("isVip", true);
        cartA.setParam("itemCount", 3);

        boolean isEligibleA = finalDiscountRule.interpret(cartA);
        System.out.println("Cart A Discount Status: " + (isEligibleA ? "APPROVED" : "DENIED"));


        // 3. Test Case B: Guest customer with standard items
        System.out.println("\n=== EVALUATING CART B ===");
        Context cartB = new Context();
        cartB.setParam("cartTotal", 5500.0);
        cartB.setParam("isVip", false); // Not a VIP
        cartB.setParam("itemCount", 4);

        boolean isEligibleB = finalDiscountRule.interpret(cartB);
        System.out.println("Cart B Discount Status: " + (isEligibleB ? "APPROVED" : "DENIED"));


        // 4. Test Case C: Guest customer matching the exact bulk rule fallback
        System.out.println("\n=== EVALUATING CART C ===");
        Context cartC = new Context();
        cartC.setParam("cartTotal", 1200.0);
        cartC.setParam("isVip", false);
        cartC.setParam("itemCount", 10); // Matches bulk order fallback

        boolean isEligibleC = finalDiscountRule.interpret(cartC);
        System.out.println("Cart C Discount Status: " + (isEligibleC ? "APPROVED" : "DENIED"));
    }
}