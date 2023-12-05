package co.ayo.jmc.concurrency.parallelstreams.arrayblockingqueue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.Stream;

record Person(String firstName, String lastName, int age) {
    private static final String[] firstNames = {"Able", "Bob", "Charlie", "Donna", "Eve", "Fred"};
    private static final String[] lastNames = {"Norton", "OHara", "Petersen", "Quincy", "Richardson", "Smith"};
    private static final Random random = new Random();

    public Person() {
        this(firstNames[random.nextInt(firstNames.length)],
                lastNames[random.nextInt(lastNames.length)],
                random.nextInt(0, 150));
    }

    @Override
    public String toString() {
        return "%s, %s (%d)".formatted(lastName, firstName, age);
    }
}

public class Main {
    private static final CopyOnWriteArrayList<Person> masterList;

    static {
        masterList = Stream.generate(Person::new)
                .distinct()
                .limit(2500)
                .collect(CopyOnWriteArrayList::new, CopyOnWriteArrayList::add, CopyOnWriteArrayList::addAll);
    }

    private static final ArrayBlockingQueue<Person> newVisitors = new ArrayBlockingQueue<>(5);

    public static void main(String[] args) {
//        Runnable producerAdd = () -> {
//            Person visitor = new Person();
//            System.out.println("Adding " + visitor);
//            boolean queued = false;
//            try {
//                queued = newVisitors.add(visitor);
//            } catch (IllegalStateException e) {
//                System.out.println("IllegalStateException!");
//            }
//            if (queued) {
//                System.out.println(newVisitors);
//            } else {
//                System.out.println("Queue is full, cannot add " + visitor);
//            }
//        };

//        Runnable producerPut = () -> {
//            Person visitor = new Person();
//            System.out.println("Adding " + visitor);
//            try {
//                newVisitors.put(visitor);
//            } catch (InterruptedException e) {
//                System.out.println("InterruptedException!");
//            }
//            System.out.println(newVisitors);
//        };

//        Runnable producerOffer = () -> {
//            Person visitor = new Person();
//            System.out.println("Adding " + visitor);
//            boolean queued = newVisitors.offer(visitor);
//            if (queued) {
//                System.out.println(newVisitors);
//            } else {
//                System.out.println("Queue is full, cannot offer " + visitor);
//            }
//        };

//        Runnable producerOfferWithTimeout = () -> {
//            Person visitor = new Person();
//            System.out.println("Adding " + visitor);
//            boolean queued = false;
//            try {
//                queued = newVisitors.offer(visitor, 1, TimeUnit.SECONDS);
//            } catch (InterruptedException e) {
//                System.out.println(e);
//            }
//            if (queued) {
//                System.out.println(newVisitors);
//            } else {
//                System.out.println("Queue is full, cannot offer " + visitor);
//            }
//        };

        Runnable producerOfferWithTimeoutWriteToFile = () -> {
            Person visitor = new Person();
            System.out.println("Queuing " + visitor);
            boolean queued = false;
            try {
                queued = newVisitors.offer(visitor, 1, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            if (queued) {
//                System.out.println(newVisitors);
            } else {
                System.out.println("Queue is full, cannot offer " + visitor);

                List<Person> tmpList = new ArrayList<>();
                newVisitors.drainTo(tmpList);
                List<String> lines = new ArrayList<>();
                tmpList.forEach(person -> lines.add(person.toString()));

                try {
                    Files.write(Path.of("basics/files/DrainedQueue.txt"), lines, StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

//        Runnable consumer = () -> {
//            String threadName = Thread.currentThread().getName();
//            System.out.println(threadName + " polling queue "+ newVisitors.size());
//            Person visitor = newVisitors.poll();
//            if (visitor != null) {
//                System.out.println(threadName + " " + visitor);
//                if (!masterList.contains(visitor)) {
//                    masterList.add(visitor);
//                    System.out.println("---> new visitor gets coupon: " + visitor);
//                }
//            }
//            System.out.println(threadName + " done " + newVisitors.size());
//        };

//        Runnable consumerWithPollTimeout = () -> {
//            String threadName = Thread.currentThread().getName();
//            System.out.println(threadName + " polling queue "+ newVisitors.size());
//            Person visitor = null;
//            try {
//                visitor = newVisitors.poll(3, TimeUnit.SECONDS);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//            if (visitor != null) {
//                System.out.println(threadName + " " + visitor);
//                if (!masterList.contains(visitor)) {
//                    masterList.add(visitor);
//                    System.out.println("---> new visitor gets coupon: " + visitor);
//                }
//            }
//            System.out.println(threadName + " done " + newVisitors.size());
//        };

        Runnable consumerWithTake = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " polling queue "+ newVisitors.size());
            Person visitor = null;
            try {
                visitor = newVisitors.take();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(threadName + " " + visitor);
            if (!masterList.contains(visitor)) {
                masterList.add(visitor);
                System.out.println("---> new visitor gets coupon: " + visitor);
            }
            System.out.println(threadName + " done " + newVisitors.size());
        };

        ScheduledExecutorService producerExecutor = Executors.newSingleThreadScheduledExecutor();
//        producerExecutor.scheduleWithFixedDelay(producerOfferWithTimeoutWriteToFile, 0, 1, TimeUnit.SECONDS);
        producerExecutor.scheduleWithFixedDelay(producerOfferWithTimeoutWriteToFile, 0, 3, TimeUnit.SECONDS);

        ScheduledExecutorService consumerExecutor = Executors.newScheduledThreadPool(3);
        for (int i = 0; i < 3; i++) {
            consumerExecutor.scheduleAtFixedRate(consumerWithTake, 6, 1, TimeUnit.SECONDS);
        }

        try {
            System.out.println("Call producer awaitTermination");
            producerExecutor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        producerExecutor.shutdownNow();

        try {
            System.out.println("Call consumer awaitTermination");
            consumerExecutor.awaitTermination(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        consumerExecutor.shutdownNow();

    }
}










































