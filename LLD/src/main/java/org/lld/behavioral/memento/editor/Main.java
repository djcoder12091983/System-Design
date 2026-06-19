package org.lld.behavioral.memento.editor;

// dummy test class
// TODO: Add actual test cases
public class Main {
    public static void main(String[] args) {
        // 1. Initialize the system components
        TextEditor wordApp = new TextEditor();
        HistoryManager history = new HistoryManager();

        System.out.println("=== USER DOCUMENT ACTIVITY ===");

        // Step A: Type initial text and save a checkpoint
        wordApp.typeText("Hello World. ");
        history.saveCheckpoint(wordApp); // Checkpoint 1

        // Step B: Type additional text and save another checkpoint
        wordApp.typeText("Design Patterns are awesome. ");
        history.saveCheckpoint(wordApp); // Checkpoint 2

        // Step C: Make mistakes or test adjustments without saving
        wordApp.typeText("This typo sentence will be deleted soon!");
        wordApp.changeCursor(5);

        System.out.println("\n=== PROCESSING BACKTRACKING UNDO SELECTION ===");

        // Undo 1: Discards Step C and rolls back to Checkpoint 2 state
        history.executeUndo(wordApp);

        // Undo 2: Discards Checkpoint 2 state and rolls back to Checkpoint 1 state
        history.executeUndo(wordApp);
    }
}