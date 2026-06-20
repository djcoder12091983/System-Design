package org.lld.behavioral;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.lld.DPTestSuit;
import org.lld.behavioral.command.automation.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

// These tests ensure that command requests wrap execution logic cleanly, decoupling the sender (RemoteControl)
// from receivers, while validating that state stacks process multi-level undo calls correctly.
@DisplayName("Command Pattern - Smart Home Automation Test Suite")
public class AutomationCommandTest extends DPTestSuit {

    private SmartLight light;
    private AirConditioner ac;
    private RemoteControl remote;

    @BeforeEach
    void initDevices() {
        light = new SmartLight("Bedrooms");
        ac = new AirConditioner(); // Default state is 24°C
        remote = new RemoteControl();
    }

    @Test
    @DisplayName("Executing a command wrapper must map directly to its specific receiver method actions")
    void testCommandExecutionDecoupling() {
        SmartHomeCommand turnOn = new TurnOnLightCommand(light);
        remote.setCommand(turnOn);

        remote.pressButton();

        String out = outputStreamCaptor.toString();
        assertTrue(out.contains("[Device] Bedrooms Light is turned ON."), "Invoker failed to pass execution down to command receiver");
    }

    @Test
    @DisplayName("The Undo engine must accurately backtrack sequential state updates in LIFO chronological order")
    void testMultiLevelUndoStateRollbacks() {
        // Step 1: Set AC to 18°C
        SmartHomeCommand setTemp18 = new SetTemperatureCommand(ac, 18);
        remote.setCommand(setTemp18);
        remote.pressButton(); // Stack state: [18]

        // Step 2: Set AC to 22°C
        SmartHomeCommand setTemp22 = new SetTemperatureCommand(ac, 22);
        remote.setCommand(setTemp22);
        remote.pressButton(); // Stack state: [18, 22]

        // Verify current active receiver value
        assertEquals(22, ac.getTemperature());
        outputStreamCaptor.reset();

        // Execution Check 1: Trigger first undo operation (Reverts 22°C -> 18°C)
        remote.pressUndoButton();
        String undoLog1 = outputStreamCaptor.toString();
        assertEquals(18, ac.getTemperature(), "First undo failed to revert to previous state snapshot values");
        assertTrue(undoLog1.contains("set to 18°C"), "Undo logging trace missing parameters");

        outputStreamCaptor.reset();

        // Execution Check 2: Trigger second undo operation (Reverts 18°C -> 24°C default)
        remote.pressUndoButton();
        String undoLog2 = outputStreamCaptor.toString();
        assertEquals(24, ac.getTemperature(), "Second undo failed to backtrack down to default foundational values");
        assertTrue(undoLog2.contains("set to 24°C"));
    }

    @Test
    @DisplayName("Invoking undo operations across an empty stack context must fail gracefully without generating exceptions")
    void testUndoGracefulFailureOnEmptyHistoryStack() {
        // Trigger undo with no history items
        remote.pressUndoButton();

        String out = outputStreamCaptor.toString();
        assertTrue(out.contains("[Remote] History empty. Nothing to undo."));
    }
}