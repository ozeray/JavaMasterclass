package co.ayo.jmc.concurrency.synchronization.challenge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoeWarehouse {

    public static final int MAX_ORDERS = 10;
    public static String[] shoeTypes = {"Sports", "Seasonal", "Classic", "Winter", "Casual"};
    public static Map<String, Integer> shoes = new HashMap<>();
    private final List<Order> orders;

    public ShoeWarehouse() {
        shoes.put("Sports", 100);
        shoes.put("Seasonal", 150);
        shoes.put("Classic", 200);
        shoes.put("Winter", 120);
        shoes.put("Casual", 140);

        this.orders = new ArrayList<>();
    }

    synchronized void receiveOrder(Order order) {
        while (orders.size() >= MAX_ORDERS) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        orders.add(order);
        notifyAll();
        System.out.printf("%s - Order received(%s): %s%n", Thread.currentThread().getName(), order, shoes.entrySet());
    }

    synchronized void fulfillOrder() {
        while (orders.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        Order lastOrder = orders.removeFirst();
        shoes.computeIfPresent(lastOrder.shoeType(), (shoe, count) -> count - lastOrder.quantity());
        notifyAll();
        System.out.printf("%s - Order fulfilled(%s): %s%n", Thread.currentThread().getName(), lastOrder, shoes.entrySet());
    }
}
