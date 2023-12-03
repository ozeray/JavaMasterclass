package co.ayo.jmc.concurrency.memory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static void main(String[] args) {
        StopWatch greenWatch = new StopWatch(TimeUnit.SECONDS);
        StopWatch purpleWatch = new StopWatch(TimeUnit.SECONDS);
        StopWatch redWatch = new StopWatch(TimeUnit.SECONDS);
        Thread purple = new Thread(() -> purpleWatch.countDown(7), ThreadColor.ANSI_PURPLE.name());
        Thread green = new Thread(greenWatch::countDown, ThreadColor.ANSI_GREEN.name());
        Thread red = new Thread(redWatch::countDown, ThreadColor.ANSI_RED.name());
        green.start();
        red.start();
        purple.start();

        while (purple.isAlive()) {
            try {
                Thread.sleep(250);
//                System.out.println("Purple: " + purple.getState() + " Green: " + green.getState() + " Red: " + red.getState());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class StopWatch {
    private TimeUnit timeUnit;
//    private int i;

    public StopWatch(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }

    public void countDown() {
        countDown(5);
    }

    public void countDown(int unitCount) {
        String threadName = Thread.currentThread().getName();
        ThreadColor threadColor = ThreadColor.ANSI_RESET;

        try {
            threadColor = ThreadColor.valueOf(threadName);
        } catch (IllegalArgumentException ignore) { }

        String color = threadColor.color();
        for (int i = unitCount; i >= 0; i--) {
            try {
                timeUnit.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("%s%s Thread : i = %d%n", color, threadName, i);
        }
    }
}