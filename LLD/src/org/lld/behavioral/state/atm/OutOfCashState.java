package org.lld.behavioral.state.atm;

// State D: Vault depleted, machine out-of-service
public class OutOfCashState implements ATMState {
    private final ATMMachine machine;

    public OutOfCashState(ATMMachine machine) { this.machine = machine; }

    @Override public void insertCard() { System.err.println("[OUT OF SERVICE] Machine empty. Card rejected."); }
    @Override public void ejectCard() { System.err.println("[OUT OF SERVICE] Hardware terminal offline."); }
    @Override public void enterPin(int pin) { System.err.println("[OUT OF SERVICE] Authentication systems disabled."); }
    @Override public void withdrawCash(int amount) { System.err.println("[OUT OF SERVICE] Out of vault resources."); }
}