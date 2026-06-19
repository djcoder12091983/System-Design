package org.lld.structural.proxy.internet;

// dummy test class
// TODO this is a dummy test class but in practice we will come up with some proper test cases
public class Main {
    public static void main(String[] args) {
        // The client code only handles the uniform interface
        InternetProvider officeInternet = new CorporateInternetProxy();

        officeInternet.connectToWebsite("stackoverflow.com"); // Allowed
        officeInternet.connectToWebsite("facebook.com");      // Blocked
        officeInternet.connectToWebsite("wikipedia.org");     // Allowed
    }
}
