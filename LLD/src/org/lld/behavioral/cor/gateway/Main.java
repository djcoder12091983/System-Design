package org.lld.behavioral.cor.gateway;

// dummy test case
// TODO: Add actual test cases
public class Main {
    public static void main(String[] args) {
        // 1. Build and link the pipeline sequence chain
        APIHandler pipelineRoot = new LoggingHandler();
        pipelineRoot.setNext(new AuthenticationHandler())
                .setNext(new RateLimitingHandler());

        // Scenario A: A perfectly formatted valid request
        System.out.println("=== TEST CASE 1: VALID REQUEST ===");
        HTTPRequest requestA = new HTTPRequest("/api/v1/checkout", "VALID_JWT_TOKEN", "10.0.0.4");
        pipelineRoot.handle(requestA);

        // Scenario B: Hacker attempts an anonymous call without a valid token
        System.out.println("\n=== TEST CASE 2: MALFORMED AUTH REQUEST ===");
        HTTPRequest requestB = new HTTPRequest("/api/v1/checkout", "BAD_EXPIRED_TOKEN", "10.0.0.4");
        pipelineRoot.handle(requestB);
        // Notice that the RateLimiting check is skipped completely because Auth fails first!

        // Scenario C: A valid token, but originating from a spamming/throttled IP address
        System.out.println("\n=== TEST CASE 3: RATE LIMITED REQUEST ===");
        HTTPRequest requestC = new HTTPRequest("/api/v1/checkout", "VALID_JWT_TOKEN", "192.168.1.99");
        pipelineRoot.handle(requestC);
    }
}
