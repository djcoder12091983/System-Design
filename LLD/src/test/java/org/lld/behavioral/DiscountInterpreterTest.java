package org.lld.behavioral;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.lld.DPTestSuit;
import org.lld.behavioral.interpreter.discount.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

// These tests evaluate grammatical syntax tree interpretations, checking both single boolean constraints
// and nested composite logic configurations (AND/OR) against variable context datasets.
@DisplayName("Interpreter Pattern - Promotion Rules Engine Test Suite")
public class DiscountInterpreterTest extends DPTestSuit {

    private Expression ruleTreeAST;

    @BeforeEach
    void buildAbstractSyntaxTree() {
        // Rule Grammar Target: (cartTotal > 5000 AND isVip == true) OR itemCount == 10
        Expression subRule1 = new GreaterThanExpression("cartTotal", 5000.0);
        Expression subRule2 = new EqualsExpression("isVip", true);
        Expression vipAndBlock = new AndExpression(subRule1, subRule2);

        Expression bulkOrderBlock = new EqualsExpression("itemCount", 10);

        ruleTreeAST = new OrExpression(vipAndBlock, bulkOrderBlock);
    }

    @Test
    @DisplayName("Interpreter must approve discount if both operands of an internal AndExpression evaluate to true")
    void testInterpreterEvaluatesAndBlockPositively() {
        Context vipHighValueCart = new Context();
        vipHighValueCart.setParam("cartTotal", 6000.0); // > 5000
        vipHighValueCart.setParam("isVip", true);       // Is VIP
        vipHighValueCart.setParam("itemCount", 2);

        boolean evaluationResult = ruleTreeAST.interpret(vipHighValueCart);

        assertTrue(evaluationResult, "Interpreter failed to parse matching true constraints inside an AND junction node");
    }

    @Test
    @DisplayName("Interpreter must deny discount if a single conditional criteria parameter fails inside the absolute AND block")
    void testInterpreterFailsAndBlockWhenSingleOperandIsFalse() {
        Context nonVipHighValueCart = new Context();
        nonVipHighValueCart.setParam("cartTotal", 7500.0); // > 5000
        nonVipHighValueCart.setParam("isVip", false);      // NOT VIP, should fail AND step
        nonVipHighValueCart.setParam("itemCount", 3);

        boolean evaluationResult = ruleTreeAST.interpret(nonVipHighValueCart);

        assertFalse(evaluationResult, "Security Error: Rules engine authorized promotion despite failing nested validation flags");
    }

    @Test
    @DisplayName("Interpreter must approve discount if the secondary backup branch of an OrExpression matches, bypassing primary failures")
    void testInterpreterEvaluatesFallbackOrBranchPositively() {
        Context guestBulkOrderCart = new Context();
        guestBulkOrderCart.setParam("cartTotal", 400.0);  // Fails main VIP rules
        guestBulkOrderCart.setParam("isVip", false);
        guestBulkOrderCart.setParam("itemCount", 10);     // Matches fallback bulk order rule perfectly!

        boolean evaluationResult = ruleTreeAST.interpret(guestBulkOrderCart);

        assertTrue(evaluationResult, "Interpreter failed to resolve matching or-expression criteria branches successfully");
    }
}