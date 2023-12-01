package co.ayo.jmc.concurrency.challenge;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) {
        Thread evenThread = new EvenThread();
        Thread oddThread = new Thread(new OddRunnable());
        evenThread.start();
        oddThread.start();

        long now = System.currentTimeMillis();
        while (evenThread.isAlive()) {
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (System.currentTimeMillis() - now > 2000) {
                evenThread.interrupt();
            }
        }
    }
}
