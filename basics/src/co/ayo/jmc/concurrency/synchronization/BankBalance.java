package co.ayo.jmc.concurrency.synchronization;

public class BankBalance {
    private String name;
    private double balance;

    private final Object lockName = new Object();
    private final Object lockBalance = new Object();

    public BankBalance(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        synchronized (lockName) {
            this.name = name;
            System.out.println("Updated " + name);
        }
    }

    public void withdraw(double amount) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (lockBalance) {
            this.balance -= amount;
            System.out.printf("Balance after withdraw(%.0f): %.0f%n", amount, balance);
        }
    }

    public void deposit(double amount) {
        synchronized (lockBalance) {
            this.balance += amount;
            System.out.printf("Balance after deposit(%.0f): %.0f%n", amount, balance);
            addPromoDollars(amount);
        }
    }

    private void addPromoDollars(double amount) {
        if (amount > 5000) {
//            synchronized (lockBalance) {
                System.out.println("Congrats. You earned a promotional deposit.");
                balance += 25;
//            }
        }
    }
}
