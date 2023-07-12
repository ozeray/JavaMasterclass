package com.ahmet.threads;

public class JvmWaitsAllThreads {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Running still");
            }
        });

        t.setDaemon(true); // Will cause JVM to stop t when main thread terminates
        t.start();
        Thread.sleep(3100);
    }
}
