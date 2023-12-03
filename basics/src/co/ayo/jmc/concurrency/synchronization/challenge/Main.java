package co.ayo.jmc.concurrency.synchronization.challenge;

import java.util.Random;
import java.util.concurrent.TimeUnit;

class Consumer implements Runnable {
    private final ShoeWarehouse shoeWarehouse;

    public Consumer(ShoeWarehouse shoeWarehouse) {
        this.shoeWarehouse = shoeWarehouse;
    }

    @Override
    public void run() {
        int counter = ShoeWarehouse.MAX_ORDERS / 2;
        while (counter > 0) {
            shoeWarehouse.fulfillOrder();
            try {
                TimeUnit.MILLISECONDS.sleep(250);
                counter--;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
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
