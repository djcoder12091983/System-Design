package org.lld.behavioral.command.automation;

import java.util.Stack;

// this is the invoker just invokes the command and command object encapsulates all detail
// like receiver, action, undo action, so invoker doesn't need to know about the receiver
// and receiver doesn't need to know about the invoker and command can execute by own using receiver

/*
Decoupling Sender and Receiver: The RemoteControl object does not have imports or references to SmartLight
or AirConditioner. It can trigger operations on any device as long as it is wrapped in a SmartHomeCommand.

Support for Composite Workflows (Macro Commands): You can easily create a PartyModeCommand class that holds
a list of multiple individual commands (TurnOnLightCommand, SetTemperatureCommand). When its execute()
method is called, it loops through and fires them all sequentially.

Asynchronous Execution & Queues: Because requests are now concrete objects floating in memory, you can feed
them into thread pools, prioritize them, delay their execution, or serialize them to disk to recover state
after a system crash.
*/

public class RemoteControl {
    private SmartHomeCommand slot;
    // Command history stack to support multi-level undo operations
    private final Stack<SmartHomeCommand> history = new Stack<>();

    public void setCommand(SmartHomeCommand command) {
        this.slot = command;
    }

    public void pressButton() {
        if (slot != null) {
            slot.execute();
            history.push(slot); // Save to history for undo capabilities
        }
    }

    public void pressUndoButton() {
        if (!history.isEmpty()) {
            SmartHomeCommand lastCommand = history.pop();
            lastCommand.undo();
        } else {
            System.out.println("[Remote] History empty. Nothing to undo.");
        }
    }
}
