package org.lld.behavioral;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.lld.DPTestSuit;
import org.lld.behavioral.state.atm.ATMMachine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

// These tests ensure that the context machine alters its execution logic completely as internal variables drift,
// validating successful paths, error checking, and out-of-service transitions.
@DisplayName("State Pattern - ATM Transaction Lifecycle Test Suite")
public class ATMStateTest extends DPTestSuit {

    private ATMMachine atm;

    @BeforeEach
    void bootAtmTerminal() {
        // Boot ATM with exactly ₹10,000 in cash reserves
        atm = new ATMMachine(10000);
    }

    @Test
    @DisplayName("ATM should successfully advance steps across the complete verification and cash dispense track")
    void testSuccessfulStateTransitionWorkflow() {
        // Initial state: NoCardState
        atm.insertCard();
        // State advances to: HasCardState
        atm.enterPin(4321);
        // State advances to: PinVerifiedState
        atm.withdrawCash(6000);

        String out = outputStreamCaptor.toString();
        assertTrue(out.contains("[ATM] Card detected."), "Failed to transition into HasCardState");
        assertTrue(out.contains("[ATM] Access Approved."), "Failed to transition into PinVerifiedState");
        assertTrue(out.contains("[Vault] Dispensing: ₹6000."), "Failed to dispense cash assets");
        assertEquals(4000, atm.getCashBalance(), "ATM balance failed to deduct properly");
    }

    @Test
    @DisplayName("ATM must deny out-of-sequence user behaviors and reject incorrect credential passes")
    void testStateGuardrailsAndSecurityExceptions() {
        // Attempting to input a PIN before inserting a card
        atm.enterPin(4321);
        String errLog1 = errorStreamCaptor.toString();
        assertTrue(errLog1.contains("[ERROR] Please insert a debit card before authentication."), "System processed illegal state operation");

        outputStreamCaptor.reset();

        // Inserting card but entering a bad PIN code
        atm.insertCard();
        atm.enterPin(0000);
        String errLog2 = errorStreamCaptor.toString();
        assertTrue(errLog2.contains("[ATM ERROR] Incorrect PIN code. Transaction terminated."), "System failed to trap bad authentication code");
    }

    @Test
    @DisplayName("Depleting the physical vault reserves must force the machine directly into a strict OutOfCash lock state")
    void testVaultDepletionLockoutBehavior() {
        atm.insertCard();
        atm.enterPin(4321);
        atm.withdrawCash(10000); // Empties machine vault completely

        String out = outputStreamCaptor.toString();
        assertTrue(out.contains("[System Alert] Hardware vault empty. Moving to out-of-service track."), "System failed to flag exhaustion boundary");

        outputStreamCaptor.reset();

        // Attempt subsequent card injection
        atm.insertCard();
        String blockLog = errorStreamCaptor.toString();
        assertTrue(blockLog.contains("[OUT OF SERVICE] Machine empty. Card rejected."), "Terminal failed to lock operations post-depletion");
    }
}
