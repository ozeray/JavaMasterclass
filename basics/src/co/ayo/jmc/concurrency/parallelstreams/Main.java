package co.ayo.jmc.concurrency.parallelstreams;

import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        long numbersLength = 100_000_000;
        long[] numbers = new Random().longs(numbersLength, 1, numbersLength).toArray();

        long delta = 0;
        int iterations = 35;

        for (int i = 0; i < iterations; i++) {
            long start = System.nanoTime();
            double averageSerial = Arrays.stream(numbers).average().orElseThrow();
            long elapsedSerial = System.nanoTime() - start;
//        System.out.printf("Ave = %.2f, elapsed = %d nanos / %.2f ms%n", averageSerial,
//                elapsedSerial, elapsedSerial / 1000000.0);

            start = System.nanoTime();
            averageSerial = Arrays.stream(numbers).parallel().average().orElseThrow();
            long elapsedParallel = System.nanoTime() - start;
            delta += (elapsedSerial - elapsedParallel);
//        System.out.printf("Ave = %.2f, elapsed = %d nanos / %.2f ms%n", averageSerial,
//                elapsedSerial, elapsedSerial / 1000000.0);
        }

        System.out.printf("Parallel is [%d] nanos / [%.2f] ms faster on average%n", delta / iterations, delta / iterations / 1000000.0);
    }
}
