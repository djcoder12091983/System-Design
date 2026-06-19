package org.lld.structural.decorator.stream;

// Concrete Decorator 2: Adds Compression Behavior
public class CompressionDecorator extends DataStreamDecorator {

    public CompressionDecorator(DataStream stream) {
        super(stream);
    }

    @Override
    public void write(String data) {
        // 1. Perform custom decoration behavior
        String compressedData = compress(data);
        System.out.println("[Decorator] Compressing data payload...");

        // 2. Pass the modified data down to the next wrapped stream layer
        super.write(compressedData);
    }

    private String compress(String data) {
        // Mock compression by removing spaces and appending a structural tag
        return "GZIP_COMPRESSED(" + data.replace(" ", "") + ")";
    }
}