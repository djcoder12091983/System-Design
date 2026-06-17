package org.lld.structural.proxy.internet;

// this interface is used to provide internet connection
// we will provide actual service as well as proxy for actual service
public interface InternetProvider {
    void connectToWebsite(String url);
}
