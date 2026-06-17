package org.lld.structural.bridge.message;

// message sending logic - 2
public class SendGridEmailSender implements MessageSender {
    @Override
    public void sendRawData(String title, String body, String recipient) {
        System.out.println("[SendGrid Email Engine] Rendering HTML Email for " + recipient);
        System.out.println("Subject: " + title + " | Content: " + body);
    }
}
