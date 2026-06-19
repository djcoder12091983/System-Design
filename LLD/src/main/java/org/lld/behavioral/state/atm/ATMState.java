package org.lld.behavioral.state.atm;

// This interface defines all possible physical user actions that can be performed on the ATM.
public interface ATMState {
    void insertCard();
    void ejectCard();
    void enterPin(int pin);
    void withdrawCash(int amount);
}
