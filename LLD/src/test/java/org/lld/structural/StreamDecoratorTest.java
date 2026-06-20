package org.lld.structural;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.lld.DPTestSuit;
import org.lld.structural.decorator.stream.CompressionDecorator;
import org.lld.structural.decorator.stream.DataStream;
import org.lld.structural.decorator.stream.EncryptionDecorator;
import org.lld.structural.decorator.stream.RawDataStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

// These tests verify that wrapping layers dynamically modifies behavior in a specific sequential order while
// maintaining standard core interface properties.
@DisplayName("Decorator Pattern - Data Stream Processing Pipeline Test Suite")
public class StreamDecoratorTest extends DPTestSuit {

    private final String samplePayload = "Secret Key Data";

    @Test
    @DisplayName("RawDataStream should process text payloads instantly without modification")
    void testBaseComponentStreamExecution() {
        DataStream baseStream = new RawDataStream();
        baseStream.write(samplePayload);

        String out = outputStreamCaptor.toString().trim();
        assertTrue(out.contains("[Disk Storage] Writing Final Raw Bytes: Secret Key Data"),
                "Base component should print raw data with no extra structural layers");
    }

    @Test
    @DisplayName("EncryptionDecorator should modify bytes sequentially before pushing data down the chain")
    void testSingleDecoratorLayerTransformation() {
        DataStream encryptedStream = new EncryptionDecorator(new RawDataStream());
        encryptedStream.write(samplePayload);

        String out = outputStreamCaptor.toString().trim();
        assertTrue(out.contains("[Decorator] Encrypting data payload..."), "Should call encryption layer logic");
        // Verify rotation cipher step calculation (+1 char shift: 'S'->'T', 'e'->'f', etc.)
        assertTrue(out.contains("Writing Final Raw Bytes: Tfasfu!Lfs!Ebub"), "Data payload failed to encrypt accurately");
    }

    @Test
    @DisplayName("Combining multiple stacked decorators must execute stream tasks matching the nesting configuration")
    void testComplexStackedDecoratorPipeline() {
        // Build a multi-layered stream processing graph: Compress -> Encrypt -> Raw
        DataStream productionPipeline = new CompressionDecorator(
                new EncryptionDecorator(
                        new RawDataStream()
                )
        );

        productionPipeline.write("User Auth");

        String out = outputStreamCaptor.toString().trim();

        // Assert correct execution ordering log traces
        assertTrue(out.indexOf("[Decorator] Compressing data payload...") < out.indexOf("[Decorator] Encrypting data payload..."),
                "Compression behavior wrapper layer must occur before encryption logic takes place");

        assertTrue(out.contains("GZIP_COMPRESSED"), "Compression decorator output missing formatting structure signature");
    }
}
