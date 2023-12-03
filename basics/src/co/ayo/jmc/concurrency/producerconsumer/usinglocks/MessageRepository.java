package co.ayo.jmc.concurrency.producerconsumer.usinglocks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class MessageRepository {
    private String message;
    private boolean hasMessage = false;

    private final Lock lock = new ReentrantLock();

    public String read() {
        try {
            if (lock.tryLock(3, TimeUnit.SECONDS)) {
                try {
                    while (!hasMessage) {
                        try {
                            TimeUnit.MILLISECONDS.sleep(250);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    hasMessage = false;
                } finally {
                    lock.unlock();
                }
            } else {
                System.out.println("** read blocked " + lock);
                // Setting hasMessage = false before reading a message => There is a chance that a message can be missed.
                // Added 3 seconds wait duration to overcome this
                hasMessage = false;
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return message;
    }

    public void write(String msg) {
        try {
            if (lock.tryLock(3, TimeUnit.SECONDS)) {
                while (hasMessage) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                hasMessage = true;
            } else {
                System.out.println("** write blocked " + lock);
                hasMessage = true;
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // There is a chance that a message write can be skipped over (Added 3 seconds wait duration to overcome this)..
        message = msg;
    }
}

