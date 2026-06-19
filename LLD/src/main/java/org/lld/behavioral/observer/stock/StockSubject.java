package org.lld.behavioral.observer.stock;

// The Subject keeps track of its subscribed observers and loops through
// them to broadcast updates whenever its data changes.
import java.util.ArrayList;
import java.util.List;

public interface StockSubject {
    void registerObserver(StockObserver observer);
    void removeObserver(StockObserver observer);
    void notifyObservers();
}