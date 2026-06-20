package org.lld.structural;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.lld.DPTestSuit;
import org.lld.structural.bridge.message.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

// These tests verify that changing the abstraction layer (e.g., UrgentMessage) or the implementation layer (e.g., TwilioSmsSender)
// does not cause runtime side effects or class variations to leak into other system branches.
@DisplayName("Bridge Pattern - Disparate Notification Streams Test Suite")
public class MessageBridgeTest extends DPTestSuit {

    @Test
    @DisplayName("UrgentMessage abstraction paired with TwilioSms implementation must append critical alert markers")
    void testUrgentMessageOverSmsBridge() {
        MessageSender smsProvider = new TwilioSmsSender();
        Message criticalAlert = new UrgentMessage("Reactor Core 4 Failure", smsProvider);

        criticalAlert.logAndDeliver("+919876543210");

        String logs = outputStreamCaptor.toString();
        assertTrue(logs.contains("[LOG: AUDIT TRAIL] Processing URGENT"), "Abstraction business logic logging failed to execute");
        assertTrue(logs.contains("[Twilio SMS Engine]"), "Implementation execution call failed to cross the Bridge link");
        assertTrue(logs.contains("!!! IMMEDIATE ACTION REQUIRED !!!"), "Message customization payload missing structural parameters");
    }

    @Test
    @DisplayName("MarketingMessage abstraction paired with SendGridEmail implementation must process promotional formatting layout signatures")
    void testMarketingMessageOverEmailBridge() {
        MessageSender emailProvider = new SendGridEmailSender();
        Message promoRun = new MarketingMessage("Buy 1 Get 1 Free Cloud Servers", emailProvider);

        promoRun.logAndDeliver("ceo@enterprise.com");

        String logs = outputStreamCaptor.toString();
        assertTrue(logs.contains("Processing low-priority Marketing broadcast"));
        assertTrue(logs.contains("[SendGrid Email Engine] Rendering HTML Email"));
        assertTrue(logs.contains("Unsubscribe reply STOP"), "Marketing infrastructure boilerplate text is missing");
    }
}

