package co.ayo.jmc.concurrency.challenge;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class EvenThread extends Thread {
    @Override
    public void run() {
        int[] nums = IntStream.rangeClosed(0, 8).filter(value -> value % 2 == 0).toArray();
        for (int num : nums) {
            System.out.print(num + " ");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.println(getName() + " interrupted.");
                break;
            }
        }
    }
}
