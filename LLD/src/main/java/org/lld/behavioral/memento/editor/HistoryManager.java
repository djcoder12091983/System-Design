package org.lld.behavioral.memento.editor;

// The Caretaker controls when checkpoints are saved or rolled back. Crucially,
// it has no insight into what parameters are stored inside the EditorState object.
import java.util.Stack;

public class HistoryManager {
    // Stores historical memento objects in LIFO order
    private final Stack<EditorState> undoStack = new Stack<>();

    public void saveCheckpoint(TextEditor editor) {
        undoStack.push(editor.save());
    }

    public void executeUndo(TextEditor editor) {
        if (undoStack.isEmpty()) {
            System.out.println("[History Engine] Nothing to undo.");
            return;
        }
        // Extract the last saved state and pass it down to the editor originator
        EditorState previousState = undoStack.pop();
        editor.restore(previousState);
    }
}
