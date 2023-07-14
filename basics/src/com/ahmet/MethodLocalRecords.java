package com.ahmet;

import java.util.Comparator;
import java.util.stream.Stream;

public class MethodLocalRecords {
    public static void main(String[] args) {
        record StrLen(String s, int l) {}

        String largestString = Stream.of("Ali", "Veli", "B")
                                     .map(s -> new StrLen(s, s.length()))
                                     .max(Comparator.comparingInt(StrLen::l))
                                     .map(sl -> sl.s)
                                     .orElse(null);
        System.out.println(largestString);
    }
}
