package org.lld.behavioral.cor.gateway;

// when request comes to an API then this will be intercepted
public abstract class APIHandler {
    private APIHandler next;

    // Links this handler to the next handler in the pipeline chain
    public APIHandler setNext(APIHandler next) {
        this.next = next;
        return next; // Returns the next handler to allow builder-style linking
    }

    // Template method controlling processing flow
    public final void handle(HTTPRequest request) {
        if (!process(request)) {
            // If processing fails or breaks the chain, stop execution immediately
            return;
        }

        // Pass the request along to the next link in the chain
        if (next != null) {
            next.handle(request);
        } else {
            System.out.println(">>> [API Gateway] Success: Request successfully routed to backend service controller!");
        }
    }

    // Each concrete handler overrides this method to execute its specific task
    // Returns true to continue the chain, false to terminate it
    protected abstract boolean process(HTTPRequest request);
}