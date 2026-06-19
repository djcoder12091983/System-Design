package org.lld.behavioral.cor.gateway;

// Concrete Link 3: Validates Rate Limiting and Flood Control Rules
public class RateLimitingHandler extends APIHandler {
    @Override
    protected boolean process(HTTPRequest request) {
        System.out.println("[Rate Limiter] Checking rate allocation quota for IP: " + request.getClientIp());
        if ("192.168.1.99".equals(request.getClientIp())) {
            System.err.println("[Rate Limiter ERROR] 429 Too Many Requests: IP quota limit exceeded. Request throttled.");
            return false; // Terminate chain execution here
        }
        System.out.println("[Rate Limiter] Quota Clear.");
        return true;
    }
}
