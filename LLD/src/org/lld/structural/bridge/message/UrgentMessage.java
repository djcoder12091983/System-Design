package org.lld.structural.bridge.message;

// Refined Abstraction - 1
public class UrgentMessage extends Message {
    private final String alertDetails;

    public UrgentMessage(String alertDetails, MessageSender sender) {
        super(sender);
        this.alertDetails = alertDetails;
    }

    @Override
    public void logAndDeliver(String recipient) {
        System.out.println("\n--- [LOG: AUDIT TRAIL] Processing URGENT high-priority alert ---");
        // Appending urgent-specific metadata to the payload
        String formattedTitle = "CRITICAL ALERT";
        String formattedBody = "!!! IMMEDIATE ACTION REQUIRED !!! Details: " + alertDetails;

        // Delegating execution over the bridge link
        sender.sendRawData(formattedTitle, formattedBody, recipient);
    }
}
