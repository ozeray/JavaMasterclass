package co.ayo.jmc.concurrency.synchronization.challenge;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        ShoeWarehouse shoeWarehouse = new ShoeWarehouse();

        Thread producer = new Thread(() -> {
            Random random = new Random();
            for (int i = 1; i <= ShoeWarehouse.MAX_ORDERS; i++) {
                Order order = new Order(i,
                        ShoeWarehouse.shoeTypes[random.nextInt(0, ShoeWarehouse.shoeTypes.length)],
                        random.nextInt(1, 5));
                try {
                    TimeUnit.MILLISECONDS.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                shoeWarehouse.receiveOrder(order);
            }
        });
        producer.start();

        Thread consumer1 = new Thread(new Consumer(shoeWarehouse));
        consumer1.start();
        Thread consumer2 = new Thread(new Consumer(shoeWarehouse));
        consumer2.start();
    }
}
