package org.lld.behavioral.cor.gateway;

// Concrete Link 2: Verifies Security Token Credentials
public class AuthenticationHandler extends APIHandler {
    @Override
    protected boolean process(HTTPRequest request) {
        System.out.println("[Auth Filter] Checking Authorization Token...");
        if ("VALID_JWT_TOKEN".equals(request.getToken())) {
            System.out.println("[Auth Filter] Token Approved.");
            return true;
        }
        System.err.println("[Auth Filter ERROR] 401 Unauthorized: Invalid or missing API security token.");
        return false; // Terminate chain execution here
    }
}