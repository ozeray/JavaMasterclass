package com.ahmet.completejavadeveloper.streams;

import java.util.OptionalInt;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        System.out.println("----------------------------------");
        OptionalInt max = IntStream.iterate(1, i -> i + 1)
                .filter(Character::isAlphabetic)
                .takeWhile(i -> i <= (int) 'g')
                .dropWhile(i -> i < (int) 'G')
                .peek(i -> System.out.printf("%c ", (char) i))
                .max();
        System.out.println();
        System.out.println((char) max.orElse(0));
    }
}
