package org.lld.behavioral.memento.editor;

// This class is completely immutable to ensure that old snapshots cannot be corrupted by subsequent changes.
// It exposes a restricted interface so only the Originator can read its fields.
public class EditorState {
    private final String content;
    private final int cursorPosition;

    // Package-private or private constructor prevents external components from spoofing snapshots
    public EditorState(String content, int cursorPosition) {
        this.content = content;
        this.cursorPosition = cursorPosition;
    }

    // Accessible only to the Originator class
    public String getContent() { return content; }
    public int getCursorPosition() { return cursorPosition; }
}