package org.lld.structural.proxy.internet;

// actual service provider on top this we will add proxy to add additional features
public class RealInternetConnection implements InternetProvider {
    @Override
    public void connectToWebsite(String url) {
        System.out.println("[Network Packet Engine] Connected to: " + url);
    }
}

