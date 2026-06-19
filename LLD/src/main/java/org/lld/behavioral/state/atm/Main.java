package org.lld.behavioral.state.atm;

// Driver class to test the ATM state pattern implementation
// TODO we will come up with a better test suite using test framework
public class Main {
    public static void main(String[] args) {
        // Initialize an ATM loaded with a total of ₹15,000 cash assets
        ATMMachine atm = new ATMMachine(15000);

        System.out.println("=== TRANSACTION RUN 1: SUCCESSFUL CASH OUT ===");
        atm.insertCard();
        atm.enterPin(4321);
        atm.withdrawCash(10000); // Leaves ₹5000 balance

        System.out.println("\n=== TRANSACTION RUN 2: BLOCKED INCORRECT FLOWS ===");
        atm.withdrawCash(2000); // Error: No card active in slot!
        atm.insertCard();
        atm.enterPin(9999);     // Error: Bad PIN, system auto-ejects card

        System.out.println("\n=== TRANSACTION RUN 3: VAULT DEPLETION LOCKOUT ===");
        atm.insertCard();
        atm.enterPin(4321);
        atm.withdrawCash(6000); // Error: Greater than available ₹5000 vault ceiling balance!

        System.out.println("\n=== TRANSACTION RUN 4: CRITICAL EXHAUSTION ===");
        atm.insertCard();
        atm.enterPin(4321);
        atm.withdrawCash(5000); // Exact draw matches balance. Empties vault completely.

        System.out.println("\n=== TRANSACTION RUN 5: POST-DEPLETION TRY ===");
        atm.insertCard(); // Fails instantly because machine entered OutOfCashState
    }
}

