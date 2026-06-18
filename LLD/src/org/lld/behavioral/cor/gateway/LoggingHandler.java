package org.lld.behavioral.cor.gateway;

// Concrete Link 1: Audits and Logs request access metadata
public class LoggingHandler extends APIHandler {
    @Override
    protected boolean process(HTTPRequest request) {
        System.out.println("[Log Filter] Intercepted request to URL: " + request.getUrl() + " from IP: " + request.getClientIp());
        return true; // Logging never blocks, proceed down the chain
    }
}