package org.lld.behavioral.template.parser;

// Concrete Subclass 2: Specializing in JSON file parsing
public class JsonDataParser extends DataParser {

    @Override
    protected byte[] extractRawBytes() {
        System.out.println("[JSON Extractor] Slurping the entire file stream into memory buffer blocks...");
        return "{ 'records': [ {'id':1, 'name':'Alice'}, {'id':2, 'name':'Bob'} ] }".getBytes();
    }

    @Override
    protected boolean parseSchema(byte[] rawData) {
        System.out.println("[JSON Parser] Tokenizing complex key-value tree hierarchies using a Jackson-style parser library.");
        return true;
    }

    // Overriding the base class Hook method to alter the standard workflow path
    @Override
    protected boolean shouldValidateData() {
        System.out.println("[JSON Config] Skipping optional heavy data validation step for speed optimization.");
        return false;
    }
}
