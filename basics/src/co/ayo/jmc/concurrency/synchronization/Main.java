package co.ayo.jmc.concurrency.synchronization;

public class Main {

    public static void main(String[] args) {
        BankBalance balance = new BankBalance("Demo", 10000);

        Thread thread1 = new Thread(() -> balance.withdraw(2500));
        Thread thread2 = new Thread(() -> balance.setName("Tim"));
        Thread thread3 = new Thread(() -> balance.withdraw(5000));
        Thread thread4 = new Thread(() -> balance.deposit(5000));
        Thread thread5 = new Thread(() -> balance.deposit(6000));

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
            thread5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
