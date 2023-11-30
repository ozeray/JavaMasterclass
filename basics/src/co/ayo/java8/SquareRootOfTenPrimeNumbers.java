package co.ayo.java8;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SquareRootOfTenPrimeNumbers {

    public static void main(String[] args) {
        Stream.iterate(2, i -> i + 1)
                .filter(i -> IntStream.rangeClosed(2, i / 2).noneMatch(n -> i % n == 0))
                .limit(10)
                .mapToDouble(Math::sqrt)
                .forEach(System.out::println);
    }
}
