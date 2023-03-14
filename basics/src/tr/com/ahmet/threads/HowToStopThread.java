package tr.com.ahmet.threads;

public class HowToStopThread {

    static class StoppableRunnable implements Runnable {

        private boolean stopRequested;

        synchronized void requestStop() {
            stopRequested = true;
        }

        synchronized boolean isStopRequested() {
            return stopRequested;
        }

        private void sleep(int millis) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            System.out.println("StoppableRunnable running");
            while (!isStopRequested()) {
                sleep(1000);
                System.out.println("...");
            }
            System.out.println("StoppableRunnable stopped");
        }
    }

    public static void main(String[] args) {
        StoppableRunnable stoppableRunnable = new StoppableRunnable();
        Thread t = new Thread(stoppableRunnable);
        t.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Requesting stop");
        stoppableRunnable.requestStop();
        System.out.println("Stop requested");
    }
}
