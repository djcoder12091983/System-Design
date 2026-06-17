package org.lld.structural.proxy.internet;

import java.util.ArrayList;
import java.util.List;

// proxy wraps the real internet class via composition
// and checks a blocklist before allowing the connection to go through.
public class CorporateInternetProxy implements InternetProvider {
    private final InternetProvider realInternet = new RealInternetConnection();
    private static final List<String> blockedSites = new ArrayList<>();

    static {
        // TODO this can be read from some external configurations
        blockedSites.add("facebook.com");
        blockedSites.add("torrentsharing.org");
        blockedSites.add("gaming-arcade.net");
    }

    @Override
    public void connectToWebsite(String url) {
        // Intercept step: Validate destination security clearance
        // placeholder job
        if (blockedSites.contains(url.toLowerCase().trim())) {
            System.err.println("[FIREWALL BLOCK] Access Denied to unsafe site: " + url);
            return;
        }

        // Pass-through step: Forward if safe
        realInternet.connectToWebsite(url);
    }
}
