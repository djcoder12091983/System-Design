package org.lld.behavioral.observer.stock;

// Dummy test class
// TODO: Add actual test cases
public class Main {
    public static void main(String[] args) {
        // 1. Initialize the Subject data core stream
        AppleStockEngine nasdaqEngine = new AppleStockEngine();

        // 2. Initialize the disparate processing engines (Observers)
        StockObserver mobileAlerts = new MobileAlertService();
        StockObserver interfaceGrid = new DashboardUiWidget();
        StockObserver highFreqBot = new AlgorithmicTradingBot();

        // 3. Subscribe components to the centralized subject stream
        nasdaqEngine.registerObserver(mobileAlerts);
        nasdaqEngine.registerObserver(interfaceGrid);
        nasdaqEngine.registerObserver(highFreqBot);

        // --- Simulating Live Market Price Flux Transactions ---

        // Tick 1: Standard minor variation
        nasdaqEngine.updateStockPrice(155.40);

        // Tick 2: Sharp drop triggers the trading bot execution rules
        nasdaqEngine.updateStockPrice(148.50);

        // Tick 3: Sharp spike triggers user threshold SMS alert
        nasdaqEngine.updateStockPrice(185.20);

        // Unsubscribing: If the UI widget closes, it gracefully detaches without crashing the engine
        System.out.println();
        nasdaqEngine.removeObserver(interfaceGrid);

        // Tick 4: Only the remaining observers receive this update
        nasdaqEngine.updateStockPrice(190.00);
    }
}