package com.ahmet.jmc.collections.storeinventory;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

enum CartType {
    PHYSICAL,
    VIRTUAL
}

public class Cart {
    private static int lastId = 1;
    private int id;
    private final Map<String, Integer> products;
    private final LocalDate date;
    private final CartType type;

    public Cart(CartType type, int days) {
        this.type = type;
        id = lastId++;
        date = LocalDate.now().minusDays(days);
        products = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public Map<String, Integer> getProducts() {
        return products;
    }

    void addItem(InventoryItem item, int qty) {
        products.merge(item.getProduct().sku(), qty, Integer::sum);

        if (!item.reserveItem(qty)) {
            System.out.println("Ouch, something went wrong, could not add item");
        }
    }

    void removeItem(InventoryItem item, int qty) {
        int current = products.get(item.getProduct().sku());
        if (current <= qty) {
            qty = current;
            products.remove(item.getProduct().sku());
            System.out.printf("Item [%s] removed from basket%n", item.getProduct().name());
        } else {
            products.merge(item.getProduct().sku(), qty, (oldVal, newVal) -> oldVal - newVal);
            System.out.printf("%d [%s]s removed%n", qty, item.getProduct().name());
        }
        item.releaseItem(qty);
    }

    void printSalesSlip(Map<String, InventoryItem> inventory) {
        double total = 0;
        System.out.println("--------------------------------");
        System.out.println("Thank you for your sale: ");
        for (var cardItem : products.entrySet()) {
            var item = inventory.get(cardItem.getKey());
            int qty = cardItem.getValue();
            double itemizedPrice = item.getPrice() * qty;
            total += itemizedPrice;
            System.out.printf("\t%s %-10s (%d)@ $%.2f = $%.2f%n",
                    cardItem.getKey(), item.getProduct().name(), qty, item.getPrice(), itemizedPrice);
        }
        System.out.printf("Total Sale: $%.2f%n", total);
        System.out.println("--------------------------------");
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", date=" + date +
                ", products=" + products +
                '}';
    }
}
