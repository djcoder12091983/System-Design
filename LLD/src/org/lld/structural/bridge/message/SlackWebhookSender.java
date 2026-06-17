package org.lld.structural.bridge.message;

// message sending logic - 3
public class SlackWebhookSender implements MessageSender {
    @Override
    public void sendRawData(String title, String body, String recipient) {
        System.out.println("[Slack Webhook Engine] Posting to channel #" + recipient);
        System.out.println("Attachment: [" + title + "] " + body);
    }
}