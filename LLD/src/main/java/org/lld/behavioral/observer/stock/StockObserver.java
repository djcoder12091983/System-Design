package org.lld.behavioral.observer.stock;

// Every component wanting to listen to the stock stream must implement this uniform contact.
public interface StockObserver {
    // Subject invokes this method to push state changes out to the listeners
    void update(String stockSymbol, double currentPrice);
}