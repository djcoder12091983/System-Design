package org.lld.behavioral.state.atm;

// State C: User authenticated, ledger cash distribution layer active
public class PinVerifiedState implements ATMState {
    private final ATMMachine machine;

    public PinVerifiedState(ATMMachine machine) { this.machine = machine; }

    @Override public void insertCard() { System.err.println("[ERROR] System processing active session."); }
    @Override public void ejectCard() { machine.getHasCardState().ejectCard(); }
    @Override public void enterPin(int pin) { System.err.println("[ERROR] User profile session already unlocked."); }

    @Override
    public void withdrawCash(int amount) {
        if (amount > machine.getCashBalance()) {
            System.err.println("[ATM ERROR] Insufficient vault funds. Requested: ₹" + amount + " | Available: ₹" + machine.getCashBalance());
            System.out.println("Displaying transaction alternatives...");
            ejectCard();
        } else {
            System.out.println("[Vault] Dispensing: ₹" + amount + ". Please collect your banknotes.");
            machine.deduceCash(amount);

            // Check if vault became exhausted
            if (machine.getCashBalance() <= 0) {
                System.out.println("[System Alert] Hardware vault empty. Moving to out-of-service track.");
                machine.setAtmState(machine.getOutOfCashState());
            } else {
                ejectCard();
            }
        }
    }
}