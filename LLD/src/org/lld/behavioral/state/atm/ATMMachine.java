package org.lld.behavioral.state.atm;

public class ATMMachine {
    // Composition: The current structural behavior layer state reference
    private ATMState currentState;

    private int cashBalance;

    // Ready-made instances of states to prevent continuous object allocation overhead
    private final ATMState noCardState;
    private final ATMState hasCardState;
    private final ATMState pinVerifiedState;
    private final ATMState outOfCashState;

    public ATMMachine(int initialCash) {
        this.cashBalance = initialCash;

        // Instantiate the operational state graph nodes
        this.noCardState = new NoCardState(this);
        this.hasCardState = new HasCardState(this);
        this.pinVerifiedState = new PinVerifiedState(this);
        this.outOfCashState = new OutOfCashState(this);

        // Establish the initial default boot state
        if (initialCash > 0) {
            this.currentState = noCardState;
        } else {
            this.currentState = outOfCashState;
        }
    }

    // State management handles
    public void setAtmState(ATMState newState) {
        this.currentState = newState;
    }

    public int getCashBalance() { return cashBalance; }
    public void deduceCash(int amount) { this.cashBalance -= amount; }

    public ATMState getNoCardState() { return noCardState; }
    public ATMState getHasCardState() { return hasCardState; }
    public ATMState getPinVerifiedState() { return pinVerifiedState; }
    public ATMState getOutOfCashState() { return outOfCashState; }

    // Client delegation interface entry points
    public void insertCard() { currentState.insertCard(); }
    public void ejectCard() { currentState.ejectCard(); }
    public void enterPin(int pin) { currentState.enterPin(pin); }
    public void withdrawCash(int amount) { currentState.withdrawCash(amount); }
}