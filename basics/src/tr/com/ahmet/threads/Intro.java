package tr.com.ahmet.threads;

public class Intro {

    public static void main(String[] args) {
        Runnable r = () -> {
            System.out.println("Name: " + Thread.currentThread().getName());
        };

        Thread t = new Thread(r, "Thread 1");
        t.start();
        Thread t2 = new Thread(r, "Thread 2");
        t2.start();

    }}
