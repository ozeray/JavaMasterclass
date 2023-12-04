package co.ayo.jmc.concurrency.executors.callable;

import co.ayo.jmc.concurrency.executors.ThreadColor;

import java.util.List;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        try (var multiExecutor = Executors.newCachedThreadPool()) {
            List<Callable<Integer>> taskList = List.of(() -> Main.sum(1, 10, 1, "red"),
                                              () -> Main.sum(10, 100, 10, "blue"),
                                              () -> sum(2, 20, 2, "green"));
            try {
                var results = multiExecutor.invokeAll(taskList);
                for (var result : results) {
                    System.out.println(result.get(500, TimeUnit.MILLISECONDS));
                }
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void submitmain(String[] args) {
        try (var multiExecutor = Executors.newCachedThreadPool()) {
            var redSum = multiExecutor.submit(() -> Main.sum(1, 10, 1, "red"));
            var blueSum = multiExecutor.submit(() -> Main.sum(10, 100, 10, "blue"));
            var greenSum = multiExecutor.submit(() -> sum(2, 20, 2, "green"));

            try {
                System.out.println(redSum.get(500, TimeUnit.SECONDS));
                System.out.println(blueSum.get(500, TimeUnit.SECONDS));
                System.out.println(greenSum.get(500, TimeUnit.SECONDS));
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (TimeoutException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static int sum(int start, int end, int delta, String colorString) {
        var threadColor = ThreadColor.ANSI_RESET;
        try {
            threadColor = ThreadColor.valueOf("ANSI_" + colorString.toUpperCase());
        } catch (IllegalArgumentException ignored) {
        }

        String color = threadColor.color();
        int sum = 0;
        for (int i = start; i <= end; i = i + delta) {
            sum += i;
        }
        System.out.println(color + Thread.currentThread().getName() + ", " + colorString + " " + sum);
        return sum;
    }
}
