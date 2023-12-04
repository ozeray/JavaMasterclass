package co.ayo.jmc.concurrency.executors;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

class ColorThreadFactory implements ThreadFactory {
    private String threadName;
    private int colorValue = 1;

    public ColorThreadFactory() {
    }

    public ColorThreadFactory(ThreadColor color) {
        this.threadName = color.name();
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        String name = threadName;
        if (name == null) {
            name = ThreadColor.values()[colorValue].name();
        }
        if (++colorValue >= ThreadColor.values().length) {
            colorValue = 1;
        }
        thread.setName(name);
        return thread;
    }
}

public class Main {

    public static void main(String[] args) {
        try (var multiExecutor = Executors.newCachedThreadPool()) {
            multiExecutor.execute(() -> Main.sum(1, 10, 1, "red"));
            multiExecutor.execute(() -> Main.sum(10, 100, 10, "blue"));
            multiExecutor.execute(() -> Main.sum(2, 20, 2, "green"));

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Start new jobs:");
            Stream.of("blue", "red", "green").forEach(color ->
                    multiExecutor.execute(() -> Main.sum(1, 10, 1, color)));

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Start more jobs:");
            Stream.of("blue", "red", "green", "black", "yellow", "purple", "cyan", "black", "yellow", "purple", "cyan").forEach(color ->
                    multiExecutor.execute(() -> Main.sum(1, 10, 1, color)));

        }
    }

    public static void fixedmain(String[] args) {
        int count = 6;
        try (var multiExecutor = Executors.newFixedThreadPool(count, new ColorThreadFactory())) {
            for (int i = 0; i < count; i++) {
                multiExecutor.execute(Main::countDown);
            }
        }
    }

    public static void singlemain(String[] args) {
        var blueExecutor = Executors.newSingleThreadExecutor(new ColorThreadFactory(ThreadColor.ANSI_BLUE));
        blueExecutor.execute(Main::countDown);

        var greenExecutor = Executors.newSingleThreadExecutor(new ColorThreadFactory(ThreadColor.ANSI_GREEN));
        greenExecutor.execute(Main::countDown);

        var yellowExecutor = Executors.newSingleThreadExecutor(new ColorThreadFactory(ThreadColor.ANSI_YELLOW));
        yellowExecutor.execute(Main::countDown);

        greenExecutor.shutdown();
        blueExecutor.shutdown();
        yellowExecutor.shutdown();
    }

    public static void manualmain(String[] args) {
        Thread blue = new Thread(Main::countDown, ThreadColor.ANSI_BLUE.name());
        Thread green = new Thread(Main::countDown, ThreadColor.ANSI_GREEN.name());
        Thread yellow = new Thread(Main::countDown, ThreadColor.ANSI_YELLOW.name());

        blue.start();
        try {
            blue.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        green.start();
        try {
            green.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        yellow.start();
        try {
            yellow.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void countDown() {
        String threadName = Thread.currentThread().getName();
        ThreadColor threadColor = ThreadColor.ANSI_RESET;
        try {
            threadColor = ThreadColor.valueOf(threadName.toUpperCase());
        } catch (IllegalArgumentException ignore) {
        }

        String color = threadColor.color();
        for (int i = 20; i >= 0; i--) {
            System.out.println(color + " " + threadName.replace("ANSI_", "") + " " + i);
        }
    }

    private static void sum(int start, int end, int delta, String colorString) {
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
    }
}
