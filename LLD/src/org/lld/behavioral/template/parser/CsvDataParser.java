package org.lld.behavioral.template.parser;

// Concrete Subclass 1: Specializing in CSV file parsing
public class CsvDataParser extends DataParser {

    @Override
    protected byte[] extractRawBytes() {
        System.out.println("[CSV Extractor] Reading text file records line-by-line using comma delimiters...");
        return "id,name,amount\n1,Alice,500\n2,Bob,300".getBytes();
    }

    @Override
    protected boolean parseSchema(byte[] rawData) {
        System.out.println("[CSV Parser] Mapping flat plain text tokens into Java Data Transfer Objects (DTOs).");
        return true;
    }
}
