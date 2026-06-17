package org.lld.structural.bridge.message;

// Refined Abstraction - 2
public class MarketingMessage extends Message {
    private final String promotionalOffer;

    public MarketingMessage(String promotionalOffer, MessageSender sender) {
        super(sender);
        this.promotionalOffer = promotionalOffer;
    }

    @Override
    public void logAndDeliver(String recipient) {
        System.out.println("\n--- [LOG: AUDIT TRAIL] Processing low-priority Marketing broadcast ---");
        String formattedTitle = "Exclusive Offer For You!";
        String formattedBody = promotionalOffer + " (Unsubscribe reply STOP)";

        sender.sendRawData(formattedTitle, formattedBody, recipient);
    }
}