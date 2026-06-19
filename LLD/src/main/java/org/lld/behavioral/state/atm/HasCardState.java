package org.lld.behavioral.state.atm;

// State B: Card accepted, verifying user PIN entry credentials
public class HasCardState implements ATMState {
    private final ATMMachine machine;

    public HasCardState(ATMMachine machine) { this.machine = machine; }

    @Override public void insertCard() { System.err.println("[ERROR] System busy. Cannot accept multiple cards simultaneously."); }

    @Override
    public void ejectCard() {
        System.out.println("[ATM] Card ejected successfully. Thank you.");
        machine.setAtmState(machine.getNoCardState()); // Fall back
    }

    @Override
    public void enterPin(int pin) {
        if (pin == 4321) { // Mock valid password check
            System.out.println("[ATM] Access Approved. Please select withdrawal amount quota.");
            machine.setAtmState(machine.getPinVerifiedState()); // Advance step
        } else {
            System.err.println("[ATM ERROR] Incorrect PIN code. Transaction terminated.");
            ejectCard();
        }
    }

    @Override public void withdrawCash(int amt) { System.err.println("[ERROR] Input PIN authorization password first."); }
}
