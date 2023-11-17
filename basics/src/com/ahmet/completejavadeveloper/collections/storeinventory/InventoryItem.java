package com.ahmet.completejavadeveloper.collections.storeinventory;

public class InventoryItem {
    private final Product product;
    private int qtyTotal;
    private int qtyReserved;
    private final int qtyReorder;
    private final int qtyLow;
    private final double price;

    public InventoryItem(Product product, int qtyTotal, int qtyLow, double price) {
        this.product = product;
        this.qtyTotal = qtyTotal;
        this.qtyReorder = qtyTotal;
        this.qtyLow = qtyLow;
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public double getPrice() {
        return price;
    }

    boolean reserveItem(int qty) {
        if (qtyTotal - qtyReserved >= qty) {
            qtyReserved += qty;
            return true;
        }
        return false;
    }

    void releaseItem(int qty) {
        qtyReserved -= qty;
    }

    boolean sellItem(int qty) {
        if (qtyTotal >= qty) {
            qtyTotal -= qty;
            qtyReserved -= qty;
            if (qtyTotal <= qtyLow) {
                placeInventoryOrder();
            }
            return true;
        }
        return false;
    }

    void placeInventoryOrder() {
        System.out.printf("Ordering qty %d : %s%n", qtyReorder, product);
    }

    @Override
    public String toString() {
        return "%s, $%.2f : [%04d, %2d]".formatted(product, price, qtyTotal, qtyReserved);
    }
}
