package org.lld.behavioral.command.automation;

// Every command must know how to execute its action and how to reverse it
public interface SmartHomeCommand {
    void execute();
    void undo();
}
