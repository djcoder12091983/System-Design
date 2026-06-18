package org.lld.behavioral.observer.stock;

// Concrete Observer 3: Evaluates programmatic high-frequency financial trades
public class AlgorithmicTradingBot implements StockObserver {
    @Override
    public void update(String stockSymbol, double price) {
        if (price < 150.00) {
            System.out.println("[Trading Bot] EXECUTION TRIGGER: Buying 100 shares of " + stockSymbol + " automatically.");
        } else {
            System.out.println("[Trading Bot] Strategy validation: Hold active assets.");
        }
    }
}