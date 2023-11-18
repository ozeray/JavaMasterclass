package com.ahmet.completejavadeveloper.random;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        System.out.printf("%1$d = %1$c%n", new Random().nextInt(97, 123)); // Random char a-z
        new Random().ints().limit(10).forEach(System.out::println);
    }
}
