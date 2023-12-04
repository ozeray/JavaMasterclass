package co.ayo.jmc.concurrency.synchronization.challenge;

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
