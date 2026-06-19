package org.lld.behavioral.template.parser;

// This class defines the invariant template method (parseData) as final so subclasses cannot alter the
// core execution sequence. The variable steps are declared as abstract methods.

public abstract class DataParser {

    // The Template Method: Finalized structure loop that cannot be overridden
    public final void parseData(String filePath) {
        System.out.println("\n--- Initiating Parse Job for: " + filePath + " ---");

        openFile(filePath);
        byte[] rawData = extractRawBytes();
        boolean success = parseSchema(rawData);

        // A Hook pattern variation: Optional step that subclasses can choose to override
        if (success && shouldValidateData()) {
            runDataSanitizationChecks();
        }

        closeFile();
        System.out.println("--- Parse Job Completed ---");
    }

    // Common invariant operations implemented directly in the base class
    private void openFile(String path) {
        System.out.println("[IO Engine] Opened system file handle to target path.");
    }

    private void closeFile() {
        System.out.println("[IO Engine] Released system file resource handle safely.");
    }

    private void runDataSanitizationChecks() {
        System.out.println("[Validation] Running standard structural schema compliance validation checks... Passed.");
    }

    // Abstract primitive steps that MUST be implemented by individual subclasses
    protected abstract byte[] extractRawBytes();
    protected abstract boolean parseSchema(byte[] rawData);

    // Hook method: Default behavior provided, but open for optional subclass overriding
    protected boolean shouldValidateData() {
        return true; // Enabled by default
    }
}