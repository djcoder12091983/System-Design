package org.lld.structural.bridge.message;

// implementation logic - 1
public class TwilioSmsSender implements MessageSender {
    @Override
    public void sendRawData(String title, String body, String recipient) {
        System.out.println("[Twilio SMS Engine] Sending text to " + recipient);
        System.out.println("Payload: " + title + " -> " + body);
    }
}