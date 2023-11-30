package co.ayo.java8;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WordsOccurringMoreThanOnce {

    public static void main(String[] args) {
        String str = "welcome to code decode and code decode welcome you";
        List<String> list = Arrays.asList(str.split(" "));
        Set<String> set = list.stream().filter(n -> Collections.frequency(list, n) > 1)
                .collect(Collectors.toSet());
        System.out.println(set);

        // More verbose method:
        System.out.println("Verbose method:");
        Map<String, Long> map = list.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        set = map.entrySet().stream().filter(e -> e.getValue() > 1).map(Map.Entry::getKey)
                .collect(Collectors.toSet());
        System.out.println(set);

    }
}
