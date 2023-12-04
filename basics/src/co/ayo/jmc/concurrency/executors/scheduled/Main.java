package co.ayo.jmc.concurrency.executors.scheduled;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

public class Main {

    public static void main(String[] args) {
        var dtf = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM, FormatStyle.LONG);

        Callable<ZonedDateTime> waitThenReturn = () -> {
            ZonedDateTime zdt = null;
            try {
                TimeUnit.SECONDS.sleep(2);
                zdt = ZonedDateTime.now();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return zdt;
        };

        var threadPool = Executors.newFixedThreadPool(2);
        List<Callable<ZonedDateTime>> callables = Collections.nCopies(4, waitThenReturn);

        try {
            System.out.println("---> " + ZonedDateTime.now().format(dtf));
            List<Future<ZonedDateTime>> futures = threadPool.invokeAll(callables);
            for (Future<ZonedDateTime> result : futures) {
                try {
                    System.out.println(result.get(1, TimeUnit.SECONDS).format(dtf));
                } catch (ExecutionException | TimeoutException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            threadPool.shutdown();
        }

//        System.out.println("---> " + ZonedDateTime.now().format(dtf));
        ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
//        scheduledExecutor.schedule(() -> System.out.println(ZonedDateTime.now().format(dtf)), 2, TimeUnit.SECONDS);
//        scheduledExecutor.shutdown();
//
//        System.out.println("---> " + ZonedDateTime.now().format(dtf));
//        scheduledExecutor = Executors.newScheduledThreadPool(4);
//        for (int i = 0; i < 4; i++) {
//            scheduledExecutor.schedule(() -> System.out.println(ZonedDateTime.now().format(dtf)), 2 * (i + 1), TimeUnit.SECONDS);
//        }
//        scheduledExecutor.shutdown();
//

        System.out.println("---> " + ZonedDateTime.now().format(dtf));
//        var scheduledExecutor = Executors.newScheduledThreadPool(4);
//        for (int i = 0; i < 4; i++) {
        var scheduledTaskFuture =  scheduledExecutor.scheduleWithFixedDelay(
                () -> System.out.println(ZonedDateTime.now().format(dtf)),
                2, 2,
                TimeUnit.SECONDS);
        //        }
        long now = System.currentTimeMillis();
        while (!scheduledTaskFuture.isDone()) {
            try {
                TimeUnit.SECONDS.sleep(2);
                if ((System.currentTimeMillis() - now) / 1000 > 10) {
                    scheduledTaskFuture.cancel(true);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        scheduledExecutor.shutdownNow();
//        try {
//            scheduledExecutor.awaitTermination(10, TimeUnit.SECONDS);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
    }
}
