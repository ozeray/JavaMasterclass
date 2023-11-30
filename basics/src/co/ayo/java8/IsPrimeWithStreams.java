package co.ayo.java8;

import java.util.stream.IntStream;

public class IsPrimeWithStreams {

    public static void main(String[] args) {
        System.out.println("7 is prime: " + isPrime(7));
        System.out.println("6 is prime: " + isPrime(6));
        System.out.println("5 is prime: " + isPrime(5));
        System.out.println("4 is prime: " + isPrime(4));
    }

    private static boolean isPrime(int num) {
        return num > 1 && IntStream.rangeClosed(2, num / 2).noneMatch(n -> num % n == 0);
    }
}
