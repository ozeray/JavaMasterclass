package co.ayo.jmc.concurrency.tools;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.spi.FileSystemProvider;
import java.util.List;

public class WatchServiceExample {

    public static void main(String[] args) {
        try {
            WatchService watchService = FileSystems.getDefault().newWatchService();
            Path dir = Paths.get(".");

            WatchKey watchKey = dir.register(watchService,
                    StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_MODIFY,
                    StandardWatchEventKinds.ENTRY_DELETE);

            boolean keepGoing = true;
            while (keepGoing) {
                try {
                    watchKey = watchService.take();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                List<WatchEvent<?>> events = watchKey.pollEvents();
                for (WatchEvent<?> event : events) {
                    Path context = (Path) event.context();
                    System.out.printf("Event type: %s - Context: %s%n", event.kind(), context);
                    if (!context.getFileName().toString().matches(".attach_pid.*") &&
                            event.kind() == StandardWatchEventKinds.ENTRY_DELETE) {
                        System.out.println("Shutting down watch service");
                        watchService.close();
                        keepGoing = false;
                        break;
                    }
                }
                watchKey.reset();
            }

            watchService.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
