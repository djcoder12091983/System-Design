package org.lld.behavioral.state.atm;

// State A: Machine is empty, waiting for a card injection
public class NoCardState implements ATMState {
    private final ATMMachine machine;

    public NoCardState(ATMMachine machine) { this.machine = machine; }

    @Override
    public void insertCard() {
        System.out.println("[ATM] Card detected. Please input your secure PIN number.");
        machine.setAtmState(machine.getHasCardState()); // Advance step
    }

    @Override public void ejectCard() { System.err.println("[ERROR] No active card located inside card reader."); }
    @Override public void enterPin(int pin) { System.err.println("[ERROR] Please insert a debit card before authentication."); }
    @Override public void withdrawCash(int amt) { System.err.println("[ERROR] Transaction blocked until card verification."); }
}