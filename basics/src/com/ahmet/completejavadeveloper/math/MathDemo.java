package com.ahmet.completejavadeveloper.math;

import java.util.Random;
import java.util.stream.IntStream;

public class MathDemo {

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Math.abs(Integer.MIN_VALUE));
        System.out.println(Math.abs(-5));
        System.out.println(Math.abs(-2147483647));

        System.out.println(Math.pow(2, 3));
        System.out.println(Math.sqrt(100));
        System.out.println(Math.random());

        System.out.println((char) 65);
        System.out.println((char) (65 + 25));
        System.out.println((char) 97);
        System.out.println((char) (97 + 25));
        System.out.printf("%1$d = %1$c%n", (int) (Math.random() * 26 + 65)); // Random char A-Z
        System.out.printf("%1$d = %1$c%n", (int) (Math.random() * 26 + 97)); // Random char a-z
        System.out.printf("%1$d = %1$c%n", new Random().nextInt(97, 123)); // Random char a-z
    }
}
