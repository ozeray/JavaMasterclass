package co.ayo.jmc.concurrency.join;

public class Main {

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            String name = Thread.currentThread().getName();
            System.out.println(name + " should take 10 dots to run.");
            for (int i = 0; i < 10; i++) {
                System.out.print(". ");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println("\nWhoops!! " + name + " interrupted.");
                    Thread.currentThread().interrupt();
                    return;
                }
            }
            System.out.println("\n" + name + " completed.");
        });
        thread.setName("DownloadThread");

        Thread installThread = new Thread(() -> {
            try {
                for (int i = 0; i < 3; i++) {
                    Thread.sleep(250);
                    System.out.println("Installation step " + (i + 1) + " is completed.");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "InstallThread");

        Thread monitoringThread = new Thread(() -> {
            long now = System.currentTimeMillis();
            while (thread.isAlive()) {
                System.out.println("\nWaiting for " + thread.getName() + " to complete");
                try {
                    Thread.sleep(1000);

                    if (System.currentTimeMillis() - now > 8000) {
                        thread.interrupt();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
        monitoringThread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (!thread.isInterrupted()) {
            installThread.start();
        } else {
            System.out.println("Previous thread was interrupted, " + installThread.getName() + " can't run.");
        }
    }
}
