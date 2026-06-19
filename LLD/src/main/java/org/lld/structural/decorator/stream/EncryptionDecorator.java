package org.lld.structural.decorator.stream;

// Concrete Decorator 1: Adds Encryption Behavior
public class EncryptionDecorator extends DataStreamDecorator {

    public EncryptionDecorator(DataStream stream) {
        super(stream);
    }

    @Override
    public void write(String data) {
        // 1. Perform custom decoration behavior
        String encryptedData = encrypt(data);
        System.out.println("[Decorator] Encrypting data payload...");

        // 2. Pass the modified data down to the next wrapped stream layer
        super.write(encryptedData);
    }

    private String encrypt(String data) {
        // Simple mock rotation cipher for demonstration purposes
        StringBuilder result = new StringBuilder();
        for (char character : data.toCharArray()) {
            result.append((char) (character + 1));
        }
        return result.toString();
    }
}