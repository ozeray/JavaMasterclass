package co.ayo.jmc.concurrency.parallelstreams.orderingreductioncollecting;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

record Person(String firstName, String lastName, int age) {
    private static final String[] firstNames = {"Able", "Bob", "Charlie", "Donna", "Eve", "Fred"};
    private static final String[] lastNames = {"Norton", "OHara", "Petersen", "Quincy", "Richardson", "Smith"};
    private static final Random random = new Random();

    public Person() {
        this(firstNames[random.nextInt(firstNames.length)],
             lastNames[random.nextInt(lastNames.length)],
             random.nextInt(0, 150));
    }

    @Override
    public String toString() {
        return "%s, %s (%d)".formatted(lastName, firstName, age);
    }
}

public class Main {

    public static void main(String[] args) {
        var persons = Stream.generate(Person::new)
                .limit(10)
                .sorted(Comparator.comparing(Person::lastName))
                .toArray();

        for (var person : persons) {
            System.out.println(person);
        }

        System.out.println("---------- WILL PRINT ORDERED ------------");
        Arrays.stream(persons)
                .parallel()
                .forEachOrdered(System.out::println);

        System.out.println("---------- WILL PRINT UNORDERED ----------");
        Arrays.stream(persons)
                .parallel()
                .forEach(System.out::println);

        System.out.println("--------------------------------------");

        int sum = IntStream.range(1, 101)
                .parallel()
                .reduce(0, Integer::sum);
        System.out.println("Sum = " + sum);

        System.out.println("------------------------------");
        String humptyDumpty = """
            Humpty Dumpty sat on a wall,
            Humpty Dumpty had a great fall,
            All the king's horses all the king's men,
            Couldn't put Humpty together again.
            """;
        var words = new Scanner(humptyDumpty).tokens().toList();
        var backTogetherAgain = words.stream()
                .parallel()
                // Doesn't work for parallel stream, because StringJoiner is not thread-safe
//              .reduce(new StringJoiner(" "), StringJoiner::add, StringJoiner::merge);
                  .reduce(" ", String::concat); // Works with parallel streams
//                .collect(Collectors.joining(" "));
        System.out.println("Back together: " + backTogetherAgain);

        System.out.println("------------------------------");
        var personsMap = Stream.generate(Person::new)
                .limit(10000)
                .parallel()
                .collect(Collectors.groupingByConcurrent(Person::lastName, Collectors.counting()));
        System.out.println(personsMap.entrySet());
        long totalPersons = 0;
        for (var count : personsMap.values()) {
            totalPersons += count;
        }
        System.out.println(totalPersons);
        System.out.println(personsMap.getClass().getName());

        System.out.println("---------------------------------");
        var lastCounts = Collections.synchronizedMap(new TreeMap<String, Long>());
//        var lastCounts = new ConcurrentSkipListMap<String, Long>();
        Stream.generate(Person::new)
                .limit(10000)
                .parallel()
                .forEach(person -> lastCounts.merge(person.lastName(), 1L, Long::sum));
        System.out.println(lastCounts);
        totalPersons = 0;
        for (var count : lastCounts.values()) {
            totalPersons += count;
        }
        System.out.println(totalPersons);
        System.out.println(lastCounts.getClass().getName());
    }
}
