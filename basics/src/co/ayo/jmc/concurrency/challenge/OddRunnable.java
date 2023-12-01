package co.ayo.jmc.concurrency.challenge;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class OddRunnable implements Runnable {
    @Override
    public void run() {
        int[] odds = IntStream.rangeClosed(1, 9).filter(value -> value % 2 == 1).toArray();
        for(int odd : odds) {
            System.out.print(odd + " ");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " interrupted.");
                break;
            }
        }
    }
}
