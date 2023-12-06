package co.ayo.jmc.concurrency.tools;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TimerExample {
    public static void main(String[] args) {
        Timer timer = new Timer(); // Single thread creator
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                String threadName = Thread.currentThread().getName();
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                System.out.println(threadName + " timer task executed at " +
                        dtf.format(LocalDateTime.now()));
            }
        };

        timer.scheduleAtFixedRate(timerTask, 0, 2000);

//        var executor = Executors.newSingleThreadScheduledExecutor();
//        executor.scheduleAtFixedRate(timerTask, 0, 2, TimeUnit.SECONDS);

        try {
            Thread.sleep(12000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        timer.cancel();
//        executor.shutdown();
    }
}
