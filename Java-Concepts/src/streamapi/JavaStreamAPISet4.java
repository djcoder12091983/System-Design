package streamapi;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// https://blog.devgenius.io/java-8-coding-and-programming-interview-questions-and-answers-62512c44f062
public class JavaStreamAPISet4 {

    public void containsDuplicate() {
        int nums[] = {10,15,8,49,25,98,98,32,15};
        List<Integer> list = Arrays.stream(nums)
                .boxed()
                .collect(Collectors.toList());
        Set<Integer> set = new HashSet<>(list);

        if(set.size() == list.size()) {
            System.out.println("No duplicates");
        }

        /* or can also try below way */
        Set<Integer> setData = new HashSet<>();
        boolean result = Arrays.stream(nums)
                .anyMatch(num -> !setData.add(num));
    }

    public void othersTest1() {
        List<String> list1 = Arrays.asList("Java", "8");
        List<String> list2 = Arrays.asList("explained", "through", "programs");

        Stream<String> concatStream = Stream.concat(list1.stream(), list2.stream());

        // Concatenated the list1 and list2 by converting them into Stream

        concatStream.forEach(str -> System.out.print(str + " "));

        // Printed the Concatenated Stream

        List<Integer> integerList = Arrays.asList(4,5,6,7,1,2,3);
        integerList.stream()
                .map(i -> i*i*i)
                .filter(i -> i>50)
                .forEach(System.out::println);

        int arr[] = { 99, 55, 203, 99, 4, 91 };
        Arrays.parallelSort(arr);
        // Sorted the Array using parallelSort()

        Arrays.stream(arr).forEach(n -> System.out.print(n + " "));
        /* Converted it into Stream and then
           printed using forEach */

        List<String> names = Arrays.asList("AA", "BB", "AA", "CC");
        List<String> nameList = names.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println(nameList);
    }

    public void othersTest2() {

        class Notes {

            int id;
            String tagName;
            long tagId;

            Notes(int id, String name, long tagId) {
                this.id = id;
                this.tagName = name;
                this.tagId = tagId;
            }

            public String getTagName() {
                return tagName;
            }

            public long getTagId() {
                return tagId;
            }
        }

        List<Notes> noteList = new ArrayList<>();
        noteList.add(new Notes(1, "note1", 11));
        noteList.add(new Notes(2, "note2", 22));
        noteList.add(new Notes(3, "note3", 33));
        noteList.add(new Notes(4, "note4", 44));
        noteList.add(new Notes(5, "note5", 55));

        noteList.add(new Notes(6, "note4", 66));


        Map<String, Long> notesRecords = noteList.stream()
                .sorted(Comparator
                        .comparingLong(Notes::getTagId)
                        .reversed()) // sorting is based on TagId 55,44,33,22,11
                .collect(Collectors.toMap
                        (Notes::getTagName, Notes::getTagId,
                                (oldValue, newValue) -> oldValue,LinkedHashMap::new));
        // consider old value 44 for dupilcate key
        // it keeps order
        System.out.println("Notes : " + notesRecords);

        List<String> names = Arrays.asList("AA", "BB", "AA", "CC");
        Map<String,Long> namesCount = names
                .stream()
                .collect(
                        Collectors.groupingBy(
                                Function.identity(), Collectors.counting()));
        System.out.println(namesCount);

        // TODO needs to check'
        /*
        Optional.ofNullable(noteList)
                .orElseGet(Collections::emptyList) // creates empty immutable list: [] in case noteLst is null
                .stream().filter(Objects::nonNull) //loop throgh each object and consider non null objects
                .map(note -> Notes::getTagName) // method reference, consider only tag name
                .forEach(System.out::println); // it will print tag names
         */
    }

    public void othersTest3() {
        List<String> names = Arrays.asList("AA", "BB", "AA", "CC");
        Map<String,Long> namesCount = names
                .stream()
                .filter(x->Collections.frequency(names, x)>1)
                .collect(Collectors.groupingBy
                        (Function.identity(), Collectors.counting()));
        System.out.println(namesCount);

        /*or you can also try using  */

        namesCount = names.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 1)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        int arr[] = {4,5,6,7,1,2,3};
        System.out.println(Arrays.stream(arr).max().getAsInt());

        String s = "debasis jana";
        Map<String, Long> map = Arrays.stream(s.split(""))
                .map(String::toLowerCase)
                .collect(Collectors
                        .groupingBy(str -> str,
                                LinkedHashMap::new, Collectors.counting()));

        // or you can also try using Function.identify() instead of LinkedHashMap

        Map<String, Long> mapObject = Arrays.stream(s.split(""))
                .map(String::toLowerCase)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }
}
