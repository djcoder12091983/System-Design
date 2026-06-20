package org.lld.behavioral;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.lld.DPTestSuit;
import org.lld.behavioral.observer.stock.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

// We will use standard JUnit 5 testing frameworks along with a console stream interceptor to verify reactive
// broadcasts, complex state transitions, and interchangeable algorithm switching.
// These tests verify that state mutations inside the subject automatically broadcast live values to
// all active subscribers, and that observers can detach gracefully without disrupting the message broker.
@DisplayName("Observer Pattern - Live Stock Market Broker Test Suite")
public class StockObserverTest extends DPTestSuit {

    private AppleStockEngine stockEngine;
    private StockObserver mobileAlerts;
    private StockObserver dashboardUi;
    private StockObserver tradingBot;

    @BeforeEach
    void setupMarketStream() {
        stockEngine = new AppleStockEngine();
        mobileAlerts = new MobileAlertService();
        dashboardUi = new DashboardUiWidget();
        tradingBot = new AlgorithmicTradingBot();

        stockEngine.registerObserver(mobileAlerts);
        stockEngine.registerObserver(dashboardUi);
        stockEngine.registerObserver(tradingBot);
    }

    @Test
    @DisplayName("Updating subject values must automatically broadcast ticks to all registered service observers")
    void testSubjectBroadcastsToAllObservers() {
        stockEngine.updateStockPrice(155.40);

        String out = outputStreamCaptor.toString();
        assertTrue(out.contains("[Dashboard UI] Updating ticker widget grid position: AAPL -> $155.4"), "UI component missed tick data");
        assertTrue(out.contains("[Trading Bot] Strategy validation: Hold active assets."), "Bot component missed tick data");
    }

    @Test
    @DisplayName("A sharp price drop below $150 should exclusively trigger the automated buy rules inside the trading bot")
    void testTargetedObserverConditionThresholds() {
        stockEngine.updateStockPrice(145.00);

        String out = outputStreamCaptor.toString();
        assertTrue(out.contains("[Trading Bot] EXECUTION TRIGGER: Buying 100 shares"), "Algorithmic bot failed to fire at target price threshold");
        assertFalse(out.contains("[Mobile Push] ALERT"), "False Positive: Mobile notification pushed below ceiling threshold");
    }

    @Test
    @DisplayName("Detached observers must stop receiving broadcast streams seamlessly")
    void testObserverUnsubscriptionLifecycle() {
        // Unsubscribe the dashboard UI component
        stockEngine.removeObserver(dashboardUi);
        outputStreamCaptor.reset();

        // Mutate subject state again
        stockEngine.updateStockPrice(195.00);

        String out = outputStreamCaptor.toString();
        assertTrue(out.contains("[Mobile Push] ALERT: AAPL crossed target!"), "Active subscriber missed update");
        assertFalse(out.contains("[Dashboard UI]"), "Security Defect: Unsubscribed observer node received price updates");
    }
}
