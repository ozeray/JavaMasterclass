package co.ayo.jmc.concurrency.executors.workstealfork;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

class RecursiveSumTask extends RecursiveTask<Long> {
    private final long[] numbers;
    private final int start;
    private final int end;
    private final int division;

    public RecursiveSumTask(long[] numbers, int start, int end, int division) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
        this.division = division;
    }

    @Override
    protected Long compute() {
        if ((end - start) <= numbers.length / division) {
            System.out.println(start + " : " + end);
            long sum = 0;
            for (int i = start; i < end; i++) {
                sum += numbers[i];
            }
            return sum;
        } else {
            int mid = (start + end) / 2;
            RecursiveSumTask leftTask = new RecursiveSumTask(numbers, start, mid, division);
            RecursiveSumTask rightTask = new RecursiveSumTask(numbers, mid, end, division);
            leftTask.fork();
            rightTask.fork();
            return leftTask.join() + rightTask.join();

        }
    }
}
public class Main {

    public static void main(String[] args) {
        int numbersLength = 100_000;
        long[] numbers = new Random().longs(numbersLength, 1, numbersLength).toArray();

        long sum = Arrays.stream(numbers).sum();
        System.out.println("sum = " + sum);

//        ForkJoinPool workStealingPool = (ForkJoinPool) Executors.newWorkStealingPool(
//                Runtime.getRuntime().availableProcessors());
        ForkJoinPool workStealingPool = ForkJoinPool.commonPool();

        List<Callable<Long>> tasks = new ArrayList<>();

        int numberOfTasks = 10;
        int splitCount = numbersLength / numberOfTasks;
        for (int i = 0; i < numberOfTasks; i++) {
            int start = i * splitCount;
            int end = start + splitCount;
            tasks.add(() -> {
                long taskSum = 0;
                for (int j = start; j < end; j++) {
                    taskSum += numbers[j];
                }
                return taskSum;
            });
        }

        // Both of the following computations (workStealingPool.invokeAll and invoke(recursiveSumTask)) is data parallelism.
        try {
            long now = System.currentTimeMillis();
            List<Future<Long>> futures = workStealingPool.invokeAll(tasks);
            System.out.println("Parallelism: " + workStealingPool.getParallelism());
            System.out.println("Pool size: " + workStealingPool.getPoolSize());
            System.out.println("Steal count: " + workStealingPool.getStealCount());

            long taskSum = 0;
            for (Future<Long> future : futures) {
                taskSum += future.get();
            }
            System.out.println("Total sum = " + taskSum);
            System.out.println("Performance: " + (System.currentTimeMillis() - now));

        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        long now = System.currentTimeMillis();
        RecursiveSumTask recursiveSumTask = new RecursiveSumTask(numbers, 0, numbersLength, 10);
        long forkJoinSum = workStealingPool.invoke(recursiveSumTask);
        System.out.println("Recursive sum = " + forkJoinSum);
        System.out.println("Performance: " + (System.currentTimeMillis() - now));

        workStealingPool.shutdown();

        System.out.println(workStealingPool.getClass().getName());
    }
}














