package org.lld.behavioral.cor.gateway;

// HTTP request object detail
public class HTTPRequest {
    private final String url;
    private final String token;
    private final String clientIp;

    public HTTPRequest(String url, String token, String clientIp) {
        this.url = url;
        this.token = token;
        this.clientIp = clientIp;
    }

    public String getUrl() { return url; }
    public String getToken() { return token; }
    public String getClientIp() { return clientIp; }
}