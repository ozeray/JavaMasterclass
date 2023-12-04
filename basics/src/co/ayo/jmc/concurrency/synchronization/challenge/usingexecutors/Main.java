package co.ayo.jmc.concurrency.synchronization.challenge.usingexecutors;

import co.ayo.jmc.concurrency.synchronization.challenge.Order;
import co.ayo.jmc.concurrency.synchronization.challenge.ShoeWarehouse;

import java.util.Random;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        ShoeWarehouse shoeWarehouse = new ShoeWarehouse();
        Random random = new Random();
        int count = 15;

        try(var producers = Executors.newFixedThreadPool(count);
            var consumers = Executors.newCachedThreadPool()) {
            for (int i = 1; i <= count; i++) {
                producers.execute(() -> {
                    Order order = new Order(random.nextInt(),
                            ShoeWarehouse.shoeTypes[random.nextInt(0, ShoeWarehouse.shoeTypes.length)],
                            random.nextInt(1, 5));
                    shoeWarehouse.receiveOrder(order);
                });
                consumers.execute(shoeWarehouse::fulfillOrder);
            }
        }
    }
}
