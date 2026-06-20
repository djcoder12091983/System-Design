package org.lld.behavioral;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.lld.DPTestSuit;
import org.lld.behavioral.template.parser.CsvDataParser;
import org.lld.behavioral.template.parser.DataParser;
import org.lld.behavioral.template.parser.JsonDataParser;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

// We will use standard JUnit 5 testing frameworks along with a console stream interceptor to verify sequential
// execution skeletons and double-dispatch data calculations.
// These tests prove structural skeleton enforcement: invariant operations (like opening and closing files)
// must run identically across all runs, while primitive abstract overrides (like CSV vs. JSON parsing) execute
// their unique variations cleanly inside the shared layout skeleton.
@DisplayName("Template Method Pattern - File Data Mining Engine Test Suite")
public class ParserTemplateTest extends DPTestSuit {

    @Test
    @DisplayName("CsvDataParser must follow the exact invariant file workflow and execute data sanitization")
    void testCsvDataParserWorkflowExecution() {
        DataParser csvParser = new CsvDataParser();

        // Execute the top-level immutable final template method
        csvParser.parseData("financials.csv");

        String logs = outputStreamCaptor.toString();

        // Assert the absolute chronological algorithmic execution order
        int openIdx = logs.indexOf("[IO Engine] Opened system file handle");
        int extractIdx = logs.indexOf("[CSV Extractor] Reading text file records");
        int parseIdx = logs.indexOf("[CSV Parser] Mapping flat plain text tokens");
        int validateIdx = logs.indexOf("[Validation] Running standard structural schema");
        int closeIdx = logs.indexOf("[IO Engine] Released system file resource");

        // Verify the step sequence was preserved perfectly
        assertTrue(openIdx < extractIdx, "File open must precede extraction");
        assertTrue(extractIdx < parseIdx, "Extraction must precede schema parsing");
        assertTrue(parseIdx < validateIdx, "Parsing must precede validation step");
        assertTrue(validateIdx < closeIdx, "Validation must precede file resource closure");
    }

    @Test
    @DisplayName("JsonDataParser must alter the standard template route by overriding the hook method")
    void testJsonDataParserHookAlteredWorkflow() {
        DataParser jsonParser = new JsonDataParser();

        jsonParser.parseData("profiles.json");

        String logs = outputStreamCaptor.toString();

        // Assert json primitive steps executed
        assertTrue(logs.contains("[JSON Extractor] Slurping the entire file stream"));
        assertTrue(logs.contains("[JSON Parser] Tokenizing complex key-value tree"));

        // Assert that the hook successfully removed the validation processing phase
        assertTrue(logs.contains("[JSON Config] Skipping optional heavy data validation"));
        assertFalse(logs.contains("[Validation] Running standard structural schema"),
                "Design Defect: Invariant validation ran despite subclass hook opting out");

        // Assert the safety file closure still finalized at the tail
        assertTrue(logs.contains("[IO Engine] Released system file resource"));
    }
}