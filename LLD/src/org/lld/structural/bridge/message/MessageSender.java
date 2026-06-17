package org.lld.structural.bridge.message;

// message sender interface which encapsulates sending logic and will be used by the message
public interface MessageSender {
    void sendRawData(String title, String body, String recipient);
}
