package streamapi;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaStreamAPISet2 extends DataSuppport {

    public void handlingNULL() {
        // Creating a stream that may contain nulls
        Stream<String> nullableStream = Stream.of("a", null, "b");

        // Filter out nulls
        Stream<String> nonNullsOnly = nullableStream.filter(Objects::nonNull);

        // Using Optional to handle potential nulls
        Optional.ofNullable(names)
                .stream()
                .forEach(System.out::println);
    }

    public void groupingAndPartitioning() {
        // Grouping by a classifier
        Map<Integer, List<String>> groupedByLength = names.stream()
                .collect(Collectors.groupingBy(String::length));

        // Grouping with downstream collector
        Map<Integer, Long> countByLength = names.stream()
                .collect(Collectors.groupingBy(
                        String::length,
                        Collectors.counting()
                ));

        // Partitioning (special case of grouping by boolean)
        Map<Boolean, List<String>> partitioned = names.stream()
                .collect(Collectors.partitioningBy(name -> name.length() > 4));
    }

    // custom collector
    class StringJoiner {
        private StringBuilder sb = new StringBuilder();
        private String delimiter;

        public StringJoiner(String delimiter) {
            this.delimiter = delimiter;
        }

        public void add(String element) {
            if (sb.length() > 0) {
                sb.append(delimiter);
            }
            sb.append(element);
        }

        public StringJoiner merge(StringJoiner other) {
            if (other.sb.length() > 0) {
                if (sb.length() > 0) {
                    sb.append(delimiter);
                }
                sb.append(other.sb);
            }
            return this;
        }

        @Override
        public String toString() {
            return sb.toString();
        }
    }

    public void customCollector() {
        Collector<String, StringJoiner, String> customJoiner = Collector.of(
                () -> new StringJoiner(", "),  // supplier
                StringJoiner::add,             // accumulator
                StringJoiner::merge,           // combiner
                StringJoiner::toString         // finisher
        );

        String joined = names.stream().collect(customJoiner);
    }

    public void parallelStrem() {
        // Sequential processing (default)
        long sequentialCount = names.stream().count();

        // Parallel processing
        long parallelCount = names.parallelStream().count();
        // OR
        long alsoParallelCount = names.stream().parallel().count();
    }

    public void debuggingStream() {
        // Using peek for debugging
        List<String> result = names.stream()
                .filter(name -> name.length() > 3)
                .peek(name -> System.out.println("After filter: " + name))
                .map(String::toUpperCase)
                .peek(name -> System.out.println("After map: " + name))
                .collect(Collectors.toList());

        // Breaking down complex pipelines
        Stream<String> filtered = names.stream()
                .filter(name -> name.length() > 3);
        // Inspect filtered if needed
        Stream<String> mapped = filtered.map(String::toUpperCase);
        // Inspect mapped if needed
        result = mapped.collect(Collectors.toList());
    }

    public void memoryEfficiency() {
        // Instead of storing intermediate results:
        List<String> longNames = new ArrayList<>();
        for (String name : names) {
            if (name.length() > 5) {
                longNames.add(name);
            }
        }
        List<String> upperCaseLongNames = new ArrayList<>();
        for (String name : longNames) {
            upperCaseLongNames.add(name.toUpperCase());
        }

        // Use a stream without intermediate collections:
        List<String> result = names.stream()
                .filter(name -> name.length() > 5)
                .map(String::toUpperCase)
                .collect(Collectors.toList());
    }

    public void shortCircuitingOperations() {
        // anyMatch, allMatch, noneMatch
        boolean hasLongName = names.stream()
                .anyMatch(name -> name.length() > 10); // Stops at first match

        // findFirst, findAny
        Optional<String> firstLongName = names.stream()
                .filter(name -> name.length() > 5)
                .findFirst(); // Stops after finding the first element

        // limit
        List<String> firstThree = names.stream()
                .limit(3)
                .collect(Collectors.toList()); // Processes at most 3 elements
    }

    public void fileProcessing() {
        // Read and process lines from a file
        try (Stream<String> lines = Files.lines(Paths.get("data.txt"))) {
            Map<String, Long> wordFrequency = lines
                    .flatMap(line -> Arrays.stream(line.split("\\s+")))
                    .map(word -> word.replaceAll("[^a-zA-Z]", "").toLowerCase())
                    .filter(word -> !word.isEmpty())
                    .collect(Collectors.groupingBy(
                            Function.identity(),
                            Collectors.counting()
                    ));

            // Find top 10 most frequent words
            wordFrequency.entrySet().stream()
                    .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                    .limit(10)
                    .forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}