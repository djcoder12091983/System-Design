package org.lld.behavioral.observer.stock;

// Each observer handles the incoming data according to its specific, isolated business responsibility.
// Concrete Observer 1: Handles customer push alert notifications
public class MobileAlertService implements StockObserver {
    @Override
    public void update(String stockSymbol, double price) {
        if (price > 180.00) {
            System.out.println("[Mobile Push] ALERT: " + stockSymbol + " crossed target! Sending SMS notification to user...");
        }
    }
}