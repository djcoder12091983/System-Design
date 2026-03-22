package streamapi;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class JavaStreamAPISet1 extends DataSuppport {

    public void fundamentals() {
        // From Collections
        Stream<String> streamFromList = names.stream();

        // From arrays
        Stream<String> streamFromArray = Arrays.stream(array);

        // Stream.of
        Stream<String> streamOf = Stream.of("Alex", "Beth", "Charlie");

        // Empty stream
        Stream<String> emptyStream = Stream.empty();

        // Infinite streams
        Stream<Integer> infiniteIntegers = Stream.iterate(0, n -> n + 1);
        Stream<Double> infiniteRandoms = Stream.generate(Math::random);
    }

    public void intermediateOperations() {
        // Filter elements based on a predicate
        Stream<String> filtered = names.stream()
                .filter(name -> name.startsWith("A"));

        // Map elements to something else
        Stream<Integer> lengths = names.stream()
                .map(String::length);

        // FlatMap for flattening nested collections
        List<List<Integer>> nestedLists = Arrays.asList(
                Arrays.asList(1, 2),
                Arrays.asList(3, 4)
        );
        Stream<Integer> flattened = nestedLists.stream()
                .flatMap(Collection::stream); // yields 1, 2, 3, 4

        // Sort elements
        Stream<String> sorted = names.stream()
                .sorted();

        // Custom sort
        Stream<String> customSorted = names.stream()
                .sorted(Comparator.comparing(String::length));

        // Distinct elements
        Stream<String> distinct = names.stream()
                .distinct();

        // Limit number of elements
        Stream<String> limited = names.stream()
                .limit(2);

        // Skip elements
        Stream<String> skipped = names.stream()
                .skip(1);

        // Peek (for debugging)
        Stream<String> peeked = names.stream()
                .peek(name -> System.out.println("Processing: " + name));
    }

    public void terminalOperations() {
        // Collect to a collection
        List<String> collected = names.stream()
                .collect(Collectors.toList());
        Set<String> asSet = names.stream()
                .collect(Collectors.toSet());
        Map<Integer, String> asMap = names.stream()
                .collect(Collectors.toMap(
                        String::length,      // key mapper
                        Function.identity(), // value mapper
                        (existing, replacement) -> existing // merge function
                ));

        // Count elements
        long count = names.stream().count();

        // Find elements
        Optional<String> any = names.stream().findAny();
        Optional<String> first = names.stream().findFirst();

        // Check conditions
        boolean allMatch = names.stream()
                .allMatch(name -> name.length() > 2);
        boolean anyMatch = names.stream()
                .anyMatch(name -> name.startsWith("A"));
        boolean noneMatch = names.stream()
                .noneMatch(name -> name.contains("X"));

        // Reduction
        Optional<String> reduced = names.stream()
                .reduce((a, b) -> a + ", " + b);
        String reducedWithIdentity = names.stream()
                .reduce("Names: ", (a, b) -> a + b);

        // ForEach (for side effects)
        names.stream().forEach(System.out::println);

        // Min/Max
        Optional<String> min = names.stream()
                .min(Comparator.naturalOrder());
        Optional<String> max = names.stream()
                .max(Comparator.comparing(String::length));

        // ToArray
        String[] array = names.stream().toArray(String[]::new);
    }

}
