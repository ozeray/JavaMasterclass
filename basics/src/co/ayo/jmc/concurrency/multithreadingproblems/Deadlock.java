package co.ayo.jmc.concurrency.multithreadingproblems;

import java.io.File;

public class Deadlock {
    public static void main(String[] args) {
        File resourceA = new File("basics/files/inputData.csv");
        File resourceB = new File("basics/files/outputData.json");

        Thread threadA = new Thread(() -> {
            String name = Thread.currentThread().getName();
            System.out.println(name + " attempting to lock resourceA (csv)");
            synchronized (resourceA) {
                System.out.println(name + " has lock on resourceA (csv)");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(name + " attempting to lock resourceB (json), " +
                        "still has a lock on resourceA (csv)");
                synchronized (resourceB) {
                    System.out.println(name + " has lock on resourceB (json)");
                }
                System.out.println(name + " has released lock on resourceB (json)");
            }
            System.out.println(name + " has released lock on resourceA (csv)");
        }, "THREAD-A");

        Thread threadB = new Thread(() -> {
//            String name = Thread.currentThread().getName();
//            System.out.println(name + " attempting to lock resourceB (json)");
//            synchronized (resourceB) {
//                System.out.println(name + " has lock on resourceB (json)");
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//                System.out.println(name + " attempting to lock resourceA (csv), " +
//                        "still has a lock on resourceB (json)");
//                synchronized (resourceA) {
//                    System.out.println(name + " has lock on resourceA (csv)");
//                }
//                System.out.println(name + " has released lock on resourceA (csv)");
//            }
//            System.out.println(name + " has released lock on resourceB (json)");
            // SOLUTION:
            String name = Thread.currentThread().getName();
            System.out.println(name + " attempting to lock resourceA (csv)");
            synchronized (resourceA) {
                System.out.println(name + " has lock on resourceA (csv)");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(name + " attempting to lock resourceB (json), " +
                        "still has a lock on resourceA (csv)");
                synchronized (resourceB) {
                    System.out.println(name + " has lock on resourceB (json)");
                }
                System.out.println(name + " has released lock on resourceB (json)");
            }
            System.out.println(name + " has released lock on resourceA (csv)");
        }, "THREAD-B");

        threadA.start();
        threadB.start();

        try {
            threadA.join();
            threadB.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
