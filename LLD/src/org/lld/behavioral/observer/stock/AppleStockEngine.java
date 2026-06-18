package org.lld.behavioral.observer.stock;

import java.util.ArrayList;
import java.util.List;

// Concrete Subject tracking live stock price ticks
public class AppleStockEngine implements StockSubject {
    private final List<StockObserver> observers = new ArrayList<>();
    private final String stockSymbol = "AAPL";
    private double currentPrice;

    @Override
    public void registerObserver(StockObserver observer) {
        observers.add(observer);
        System.out.println("[Stock Engine] Registered new system subscriber.");
    }

    @Override
    public void removeObserver(StockObserver observer) {
        observers.remove(observer);
        System.out.println("[Stock Engine] Removed system subscriber.");
    }

    @Override
    public void notifyObservers() {
        // Broadcast updates sequentially to every registered observer node
        for (StockObserver observer : observers) {
            observer.update(stockSymbol, currentPrice);
        }
    }

    // Business logic to simulate stock market price volatility updates
    public void updateStockPrice(double newPrice) {
        System.out.println("\n=== LIVE TICKER MUTATION: " + stockSymbol + " moved to $" + newPrice + " ===");
        this.currentPrice = newPrice;

        // Trigger the automatic broadcast sequence instantly
        notifyObservers();
    }
}
