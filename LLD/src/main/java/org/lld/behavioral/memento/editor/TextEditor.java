package org.lld.behavioral.memento.editor;

// This class holds the active data state that the user interacts with.
// It contains the business logic to generate a new EditorState checkpoint or load an existing one.
public class TextEditor {
    private String content = "";
    private int cursorPosition = 0;

    public void typeText(String text) {
        this.content += text;
        this.cursorPosition = content.length();
        System.out.println("[Editor Workspace] Typed: \"" + text + "\" | Current Doc: " + getDetails());
    }

    public void changeCursor(int newPos) {
        this.cursorPosition = newPos;
        System.out.println("[Editor Workspace] Moved cursor to index: " + cursorPosition);
    }

    private String getDetails() {
        return "{" + content + "} (Cursor at: " + cursorPosition + ")";
    }

    // 1. Factory method to create a new Memento checkpoint
    public EditorState save() {
        System.out.println(">>> [System] Snapshot created successfully.");
        return new EditorState(this.content, this.cursorPosition);
    }

    // 2. State restoration mechanism using a Memento checkpoint
    public void restore(EditorState memento) {
        if (memento == null) {
            System.out.println("[System Error] No backup history available.");
            return;
        }
        this.content = memento.getContent();
        this.cursorPosition = memento.getCursorPosition();
        System.out.println("[Undo Action] Reverted back to version: " + getDetails());
    }
}
