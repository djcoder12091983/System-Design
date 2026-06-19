package org.lld.structural.bridge.message;

// The Abstraction holding the "Bridge" bridge reference
public abstract class Message {
    // The Bridge: Composition linking abstraction to implementation
    protected MessageSender sender;

    protected Message(MessageSender sender) {
        this.sender = sender;
    }

    // High-level operational method decoupled from the platform
    public abstract void logAndDeliver(String recipient);
}