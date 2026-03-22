package streamapi;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

// https://blog.devgenius.io/java-8-coding-and-programming-interview-questions-and-answers-62512c44f062
public class JavaStreamAPISet3 {

    public void evenNumber() {
        List<Integer> list = Arrays.asList(10,15,8,49,25,98,32);
        list.stream()
                .filter(n -> n%2 == 0)
                .forEach(System.out::println);

        /* or can also try below method */

        /* When numbers are given as */
        int[] arr = {10,15,8,49,25,98,32};

        Map<Boolean, List<Integer>> result = Arrays.stream(arr).boxed()
                .collect(Collectors.partitioningBy(num -> num % 2 == 0));
        System.out.println(result);
    }

    public void numberStartingWithOne() {
        List<Integer> myList = Arrays.asList(10,15,8,49,25,98,32);
        myList.stream()
                .map(s -> s + "") // Convert integer to String
                .filter(s -> s.startsWith("1"))
                .forEach(System.out::println);

        /* or can also try below method */

        /* When numbers are given as */
        int[] arr = {10,15,8,49,25,98,32};
        List<String> list = Arrays.stream(arr).boxed()
                .map(s -> s + "")
                .filter(s -> s.startsWith("1"))
                .collect(Collectors.toList());

        System.out.println(list);
    }

    public void duplicateElements() {
        List<Integer> myList = Arrays.asList(10,15,8,49,25,98,98,32,15);
        final Set<Integer> set = new HashSet<>();
        myList.stream()
                .filter(n -> !set.add(n))
                .forEach(System.out::println);

        /* Way 1 - Gives list of all distinct/unique values */

        myList = Arrays.asList(1, 1, 85, 6, 2, 3, 65, 6, 45, 45, 5662, 2582, 2, 2, 266, 666, 656);
        myList.stream().distinct().forEach(noDuplicateData -> System.out.println(noDuplicateData));

        /* Way 2 -  Gives list of all distinct/unique values */

        myList = Arrays.asList(1, 1, 85, 6, 2, 3, 65, 6, 45, 45, 5662, 2582, 2, 2, 266, 666, 656);
        final Set<Integer> set1 = new HashSet<>(myList);

        // Convert the set back to a list if needed
        List<Integer> uniqueData = set1.stream().collect(Collectors.toList());

        // Print the unique elements
        uniqueData.forEach(System.out::println);

        /* Way 3 - Gives list of all distinct/unique values */

        /* When numbers are given as */
        int[] arr = {10,15,8,49,25,98,98,32,15};

        List<Integer> list = Arrays.stream(arr).boxed().distinct()
                .collect(Collectors.toList());
    }

    public void findFirstElement() {
        List<Integer> myList = Arrays.asList(10,15,8,49,25,98,98,32,15);
        myList.stream()
                .findFirst()
                .ifPresent(System.out::println);

        /* or can also try below single line code */
        /* When numbers are given as */
        int[] arr = {10,15,8,49,25,98,98,32,15};
        Arrays.stream(arr).boxed().findFirst().ifPresent(System.out::print);
    }

    public void findTheTotalNumberOfElements() {
            List<Integer> myList = Arrays.asList(10,15,8,49,25,98,98,32,15);
            long count =  myList.stream()
                    .count();
            System.out.println(count);

            /* or can also try below line code */
            /* When numbers are given as */
            int[] arr = {10,15,8,49,25,98,98,32,15};
            Arrays.stream(arr).boxed().count();
    }

    public void findMaxElement() {
        List<Integer> myList = Arrays.asList(10,15,8,49,25,98,98,32,15);
        int max =  myList.stream()
                .max(Integer::compare)
                .get();
        System.out.println(max);

        /* or we can try using below way */
        /* When numbers are given as */
        int[] arr = {10,15,8,49,25,98,98,32,15};

        int maxdata = Arrays.stream(arr).boxed()
                .max(Comparator.naturalOrder()).get();

        System.out.println(maxdata);
    }

    public void firstNonRepeated() {
        String input = "Java articles are Awesome";

        Character result = input.chars() // Stream of String
                .mapToObj(s -> Character.toLowerCase(Character.valueOf((char) s))) // First convert to Character object and then to lowercase
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting())) //Store the chars in map with count
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 1L)
                .map(entry -> entry.getKey())
                .findFirst()
                .get();
        System.out.println(result);

        /* or can also try using */

        input.chars().mapToObj(c -> (char) c)
                .filter(ch -> input.indexOf(ch) == input.lastIndexOf(ch))
                .findFirst().orElse(null);
    }

    public void FirstRepeated() {
        String input = "Java Articles are Awesome";

        Character result = input.chars() // Stream of String
                .mapToObj(s -> Character.toLowerCase(Character.valueOf((char) s))) // First convert to Character object and then to lowercase
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting())) //Store the chars in map with count
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 1L)
                .map(entry -> entry.getKey())
                .findFirst()
                .get();
        System.out.println(result);

        /* or can also try */

        Set<Character> seenCharacters = new HashSet<>();

        result = input.chars()
                .mapToObj(c -> (char) c)
                .filter(c -> !seenCharacters.add(c))
                .findFirst()
                .orElse(null);
    }

    public void sortValues() {
        List<Integer> myList = Arrays.asList(10,15,8,49,25,98,98,32,15);

        myList.stream()
                .sorted()
                .forEach(System.out::println);

        /* Or can also try below way */
        /* When numbers are given as */
        int[] arr = {10,15,8,49,25,98,98,32,15};

        Arrays.stream(arr).boxed().sorted().collect(Collectors.toList());

        // descending order
        myList = Arrays.asList(10,15,8,49,25,98,98,32,15);

        myList.stream()
                .sorted(Collections.reverseOrder())
                .forEach(System.out::println);
    }

}
