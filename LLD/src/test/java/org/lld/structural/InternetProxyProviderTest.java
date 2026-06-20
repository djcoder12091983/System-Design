package org.lld.structural;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.lld.DPTestSuit;
import org.lld.structural.proxy.internet.CorporateInternetProxy;
import org.lld.structural.proxy.internet.InternetProvider;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

// These tests verify that the proxy acts as a secure firewall checkpoint—intercepting unsafe URLs
// and blocking traffic before a packet engine connection is ever established.
public class InternetProxyProviderTest extends DPTestSuit {

    @Test
    @DisplayName("CorporateInternetProxy should seamlessly authorize access to clean educational sites")
    void testProtectionProxyAllowsSafeWebsites() {
        InternetProvider proxyFirewall = new CorporateInternetProxy();

        proxyFirewall.connectToWebsite("stackoverflow.com");

        String logs = outputStreamCaptor.toString().trim();
        assertTrue(logs.contains("[Network Packet Engine] Connected to: stackoverflow.com"),
                "Safe network calls must traverse the proxy smoothly to hit the real object");
    }

    @Test
    @DisplayName("CorporateInternetProxy should block restricted blocklist websites directly at the boundary layer")
    void testProtectionProxyInterceptsAndBlocksMaliciousWebsites() {
        InternetProvider proxyFirewall = new CorporateInternetProxy();

        proxyFirewall.connectToWebsite("facebook.com");

        String logs = outputStreamCaptor.toString().trim();

        // Assert the proxy's defensive firewall behavior
        assertTrue(logs.contains("[FIREWALL BLOCK] Access Denied to unsafe site: facebook.com"),
                "Proxy failed to intercept and deny access to a blocked site resource");

        // Assert that the real network engine was NEVER invoked
        assertFalse(logs.contains("[Network Packet Engine]"),
                "Security Breach: Core service network layer was reached for a blocked URL string");
    }
}