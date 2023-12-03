package co.ayo.jmc.concurrency.producerconsumer.usinglocks;

import java.util.Random;
import java.util.concurrent.TimeUnit;

class MessageWriter implements Runnable {
    private final MessageRepository ongoingMessage;
    private final String text = """
            Humpty Dumpty sat on a wall,
            Humpty Dumpty had a great fall,
            All the king's horses all the king's men,
            Couldn't put Humpty together again.""";

    public MessageWriter(MessageRepository ongoingMessage) {
        this.ongoingMessage = ongoingMessage;
    }

    @Override
    public void run() {
        Random random = new Random();
        String[] lines = text.split("\n");
        for (int i = 0; i < lines.length; i++) {
            ongoingMessage.write(lines[i]);
            try {
                TimeUnit.MILLISECONDS.sleep(random.nextInt(500, 2000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        ongoingMessage.write("Finished");
    }
}

class MessageReader implements Runnable {
    private final MessageRepository incomingMessage;

    public MessageReader(MessageRepository incomingMessage) {
        this.incomingMessage = incomingMessage;
    }

    @Override
    public void run() {
        Random random = new Random();
        String latestMessage;

        do {
            try {
                TimeUnit.MILLISECONDS.sleep(random.nextInt(500, 2000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            latestMessage = incomingMessage.read();
            System.out.println(latestMessage);
        } while (!"Finished".equals(latestMessage));
    }
}

public class Main {
    public static void main(String[] args) {
        MessageRepository messageRepository = new MessageRepository();
        Thread producer = new Thread(new MessageWriter(messageRepository), "Producer");
        Thread consumer = new Thread(new MessageReader(messageRepository), "Consumer");

        producer.setUncaughtExceptionHandler((thread, exc) -> {
            System.out.println(thread.getName() + " caught exception: " + exc);
            if (consumer.isAlive()) {
                System.out.println("Going to interrupt the consumer");
                consumer.interrupt();
            }
        });
        consumer.setUncaughtExceptionHandler((thread, exc) -> {
            System.out.println(thread.getName() + " caught exception: " + exc);
            if (producer.isAlive()) {
                System.out.println("Going to interrupt the producer");
                producer.interrupt();
            }
        });

        producer.start();
        consumer.start();
    }
}
