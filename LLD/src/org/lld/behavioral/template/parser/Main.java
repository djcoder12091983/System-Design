package org.lld.behavioral.template.parser;

// Driver class to test the template pattern implementation
// TODO come up with a better test suite using test framework

public class Main {
    public static void main(String[] args) {
        // 1. Process a CSV file using the template workflow
        DataParser csvProcessor = new CsvDataParser();
        csvProcessor.parseData("transactions_march.csv");

        // 2. Process a JSON file using the same template workflow shell
        DataParser jsonProcessor = new JsonDataParser();
        jsonProcessor.parseData("user_profiles_dump.json");
    }
}
