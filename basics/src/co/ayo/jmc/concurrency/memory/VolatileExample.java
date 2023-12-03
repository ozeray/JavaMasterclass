package co.ayo.jmc.concurrency.memory;

import java.util.concurrent.TimeUnit;

public class VolatileExample {

    public static void main(String[] args) {
        CachedData cachedData = new CachedData();

        Thread thread1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
                cachedData.toggleFlag();
                System.out.println("1: " + cachedData.isReady());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(() -> {
            while (!cachedData.isReady()) {
                //Do some time-consuming stuff
            }
            System.out.println("2: " + cachedData.isReady());
        });

        thread1.start();
        thread2.start();
    }
}

class CachedData {
    private volatile boolean flag = false;

    boolean isReady() {
        return flag;
    }

    void toggleFlag() {
        flag = !flag;
    }
}