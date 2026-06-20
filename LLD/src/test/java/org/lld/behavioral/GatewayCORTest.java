package org.lld.behavioral;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.lld.DPTestSuit;
import org.lld.behavioral.cor.gateway.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

// These tests verify that requests pass through filters sequentially, and that a failure at any single
// link short-circuits the pipeline immediately to safeguard subsequent processing nodes.
@DisplayName("Chain of Responsibility Pattern - API Gateway Test Suite")
public class GatewayCORTest extends DPTestSuit {

    private APIHandler pipelineChain;

    @BeforeEach
    void assemblePipeline() {
        // Construct the sequential check pipeline: Logging -> Auth -> RateLimiting
        pipelineChain = new LoggingHandler();
        pipelineChain.setNext(new AuthenticationHandler())
                .setNext(new RateLimitingHandler());
    }

    @Test
    @DisplayName("A completely valid request must traverse all filters to successfully hit the core service controller")
    void testValidRequestTraversesFullChain() {
        HTTPRequest validRequest = new HTTPRequest("/api/v1/checkout", "VALID_JWT_TOKEN", "10.0.0.4");

        pipelineChain.handle(validRequest);

        String out = outputStreamCaptor.toString();
        assertTrue(out.contains("[Log Filter] Intercepted request"), "Logging failed to process");
        assertTrue(out.contains("[Auth Filter] Token Approved"), "Authentication failed to process");
        assertTrue(out.contains("[Rate Limiter] Quota Clear"), "Rate limiting failed to process");
        assertTrue(out.contains("Success: Request successfully routed to backend"), "Request failed to reach core target");
    }

    @Test
    @DisplayName("An unauthorized request must break the chain immediately, skipping subsequent filters")
    void testFailedAuthShortCircuitsPipeline() {
        HTTPRequest badAuthRequest = new HTTPRequest("/api/v1/checkout", "EXPIRED_TOKEN", "10.0.0.4");

        pipelineChain.handle(badAuthRequest);

        String out = outputStreamCaptor.toString();
        assertTrue(out.contains("[Log Filter] Intercepted request"), "Logging should still execute first");
        assertTrue(out.contains("[Auth Filter ERROR] 401 Unauthorized"), "Auth should explicitly fail request");

        // Assert short-circuit protection boundaries
        assertFalse(out.contains("[Rate Limiter]"), "Security Flaw: Rate Limiter ran despite Auth failure");
        assertFalse(out.contains("Success: Request successfully routed"), "Pipeline error: Invalid request hit backend controller");
    }

    @Test
    @DisplayName("A valid token from a blacklisted IP must short-circuit at the final RateLimiting filter link")
    void testRateLimiterCatchesSpammingIP() {
        HTTPRequest throttledRequest = new HTTPRequest("/api/v1/checkout", "VALID_JWT_TOKEN", "192.168.1.99");

        pipelineChain.handle(throttledRequest);

        String out = outputStreamCaptor.toString();
        assertTrue(out.contains("[Auth Filter] Token Approved"), "Auth should pass credentials smoothly");
        assertTrue(out.contains("[Rate Limiter ERROR] 429 Too Many Requests"), "Rate limiter failed to block malicious IP");
        assertFalse(out.contains("Success: Request successfully routed"), "Throttled request erroneously hit back-end layer");
    }
}

