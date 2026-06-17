package org.lld.structural.decorator.stream;

// dummy test class
// TODO this is a dummy test class but in practice we will come up with some proper test cases
public class Main {
    public static void main(String[] args) {
        String sensitivePayload = "Confidential Transaction Amount: INR 500,000";

        // Scenario 1: A basic pipeline that writes standard raw data
        System.out.println("=== PIPELINE 1: RAW DATA ===");
        DataStream basicPipeline = new RawDataStream();
        basicPipeline.write(sensitivePayload);

        // Scenario 2: A pipeline that encrypts data before writing it
        System.out.println("\n=== PIPELINE 2: SECURE DATA ===");
        DataStream securePipeline = new EncryptionDecorator(new RawDataStream());
        securePipeline.write(sensitivePayload);

        // Scenario 3: A complex pipeline that compresses AND then encrypts the data
        System.out.println("\n=== PIPELINE 3: HIGHLY DECORATED PIPELINE ===");
        DataStream completePipeline = new CompressionDecorator(
                new EncryptionDecorator(
                        new RawDataStream()
                )
        );

        // This execution ripples down through Compression -> Encryption -> RawDataStream
        completePipeline.write(sensitivePayload);
    }
}
