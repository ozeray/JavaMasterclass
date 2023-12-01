package co.ayo.jmc.concurrency;

import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
//        Thread customThread = new CustomThread();
//        customThread.setPriority(Thread.MAX_PRIORITY);
//        customThread.setName("Deneme");
//        System.out.println(customThread);
//        System.out.println(customThread.getName());
//        System.out.println(customThread.threadId());
//        System.out.println(customThread.getThreadGroup());
//        System.out.println(customThread.getPriority());
//        customThread.start();

        Runnable myRunnable = () -> {
            for (int i = 0; i < 10; i++) {
                System.out.print("1 ");
                try {
                    TimeUnit.MILLISECONDS.sleep(1000);
                    System.out.println("A. State = " + Thread.currentThread().getState());
                } catch (InterruptedException e) {
                    System.out.println(Thread.currentThread().getName() + " interrupted!");
                    System.out.println("A1. State = " + Thread.currentThread().getState());
                    return;
                }
            }
        };
        Thread runnableThread = new Thread(myRunnable);
        runnableThread.start();

//        customThread.interrupt();

        for (int i = 0; i < 3; i++) {
            System.out.print("2 ");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long now = System.currentTimeMillis();
        while (runnableThread.isAlive()) {
            System.out.println("\nWaiting for thread to complete");
            try {
                Thread.sleep(1000);
                System.out.println("B. State = " + runnableThread.getState());

                if (System.currentTimeMillis() - now > 2000) {
                    runnableThread.interrupt();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("C. State = " + runnableThread.getState());
    }
}
