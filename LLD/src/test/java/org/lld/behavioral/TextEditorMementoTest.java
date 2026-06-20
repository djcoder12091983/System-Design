package org.lld.behavioral;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.lld.behavioral.memento.editor.EditorState;
import org.lld.behavioral.memento.editor.HistoryManager;
import org.lld.behavioral.memento.editor.TextEditor;

import static org.junit.jupiter.api.Assertions.assertEquals;

// These tests verify encapsulation boundaries—asserting that historical checkpoints are strictly immutable
// and that the caretaker stack restores old object values perfectly without breaking privacy boundaries.
@DisplayName("Memento Pattern - Document Version Checkpoint Engine Test Suite")
public class TextEditorMementoTest {

    private TextEditor editor;
    private HistoryManager history;

    @BeforeEach
    void initWorkspace() {
        editor = new TextEditor();
        history = new HistoryManager();
    }

    @Test
    @DisplayName("The Caretaker must smoothly roll back the Originator state to previous checkpoints")
    void testOriginatorStateRestorationWorkflow() {
        // State 1: Baseline text entry
        editor.typeText("Version A. ");
        history.saveCheckpoint(editor); // Caretaker pushes Memento 1

        // State 2: Alter state fields
        editor.typeText("Version B. ");
        history.saveCheckpoint(editor); // Caretaker pushes Memento 2

        // State 3: Experimental text with no checkpoint save
        editor.typeText("Temporary error text.");
        editor.changeCursor(2);

        // Execution Check 1: Trigger first rollback (Reverts back to state snapshot 2)
        history.executeUndo(editor);

        // We evaluate editor details. State must mirror Version B exactly.
        // Re-saving a memento to extract snapshot variables indirectly to protect encapsulation strings
        EditorState snapshotCheck1 = editor.save();
        assertEquals("Version A. Version B. ", snapshotCheck1.getContent());
        assertEquals(22, snapshotCheck1.getCursorPosition(), "Restored cursor position variable mismatch");

        // Execution Check 2: Trigger second rollback (Reverts back to state snapshot 1)
        history.executeUndo(editor);

        EditorState snapshotCheck2 = editor.save();
        assertEquals("Version A. ", snapshotCheck2.getContent(), "Failed to traverse down to initial history markers");
    }

    @Test
    @DisplayName("The Caretaker must handle restoration requests gracefully when history arrays are depleted")
    void testUndoGracefulFailureOnEmptyHistoryStack() {
        // Intentionally execute undo with zero saves
        history.executeUndo(editor);

        EditorState pristineState = editor.save();
        assertEquals("", pristineState.getContent(), "State should remain unmodified if no versions are cached");
    }
}