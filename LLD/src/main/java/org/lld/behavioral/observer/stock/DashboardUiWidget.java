package org.lld.behavioral.observer.stock;

// Concrete Observer 2: Handles live dashboard UI chart rendering modifications
public class DashboardUiWidget implements StockObserver {
    @Override
    public void update(String stockSymbol, double price) {
        System.out.println("[Dashboard UI] Updating ticker widget grid position: " + stockSymbol + " -> $" + price);
    }
}