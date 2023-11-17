package com.ahmet.completejavadeveloper.collections.storeinventory;

import java.time.LocalDate;
import java.util.*;

public class Store {
    private final Random random = new Random();
    private Map<String, InventoryItem> inventory;
    private final NavigableSet<Cart> carts = new TreeSet<>(Comparator.comparing(Cart::getId));
    private Map<Category, Map<String, InventoryItem>> aisleInventory;

    public static void main(String[] args) {
        Store store = new Store();
        store.stockStore();
        store.listInventory();

        store.stockAisles();
        store.listProductsByCategory();

        store.manageStoreCarts();
        store.listInventory();

        store.carts.forEach(System.out::println);

        store.abandonCartsBeforeToday();
        store.listInventory();
        store.carts.forEach(System.out::println);
    }

    void manageStoreCarts() {
        Cart cart1 = new Cart(CartType.PHYSICAL, 1);
        carts.add(cart1);

        InventoryItem item = aisleInventory.get(Category.PRODUCE).get("apple");
        cart1.addItem(item, 6);
        cart1.addItem(aisleInventory.get(Category.PRODUCE).get("pear"), 5);
        cart1.addItem(aisleInventory.get(Category.BEVERAGE).get("coffee"), 1);
        cart1.removeItem(aisleInventory.get(Category.PRODUCE).get("pear"), 2);
        System.out.println(cart1);

        Cart cart2 = new Cart(CartType.VIRTUAL, 1);
        carts.add(cart2);
        cart2.addItem(inventory.get("L103"), 20);
        cart2.addItem(inventory.get("B100"), 10);
        System.out.println(cart2);

        Cart cart3 = new Cart(CartType.VIRTUAL, 0);
        carts.add(cart3);
        cart3.addItem(inventory.get("R777"), 998);
        System.out.println(cart3);
        if (!checkoutCart(cart3)) {
            System.out.println("Something went wrong, could not check out.");
        }

        Cart cart4 = new Cart(CartType.PHYSICAL, 0);
        carts.add(cart4);
        cart4.addItem(aisleInventory.get(Category.BEVERAGE).get("tea"), 1);
        System.out.println(cart4);

    }

    boolean checkoutCart(Cart cart) {
        for (var cartItem : cart.getProducts().entrySet()) {
            var item = inventory.get(cartItem.getKey());
            int qty = cartItem.getValue();
            if (!item.sellItem(qty)) {
                return false;
            }
        }
        cart.printSalesSlip(inventory);
        carts.remove(cart);
        return true;
    }

    void abandonCartsBeforeToday() {
        int dayOfYear = LocalDate.now().getDayOfYear();
        Cart lastCart = null;
        for (Cart cart : carts) {
            if (cart.getDate().getDayOfYear() == dayOfYear) {
                break;
            }
            lastCart = cart;
        }
        var oldCarts = carts.headSet(lastCart, true);
        Cart abandonedCart;
        while ((abandonedCart = oldCarts.pollFirst()) != null) {
            for (String sku : abandonedCart.getProducts().keySet()) {
                InventoryItem item = inventory.get(sku);
                item.releaseItem(abandonedCart.getProducts().get(sku));
            }
        }
    }

    void listProductsByCategory() {
        aisleInventory.keySet().forEach(k -> {
            System.out.println("--------\n" + k + "\n--------");
            aisleInventory.get(k).keySet().forEach(System.out::println);
        });
    }

    void stockStore() {
        inventory = new HashMap<>();
        List<Product> products = Arrays.asList(
                new Product("A100", "apple", "local", Category.PRODUCE),
                new Product("B100", "banana", "local", Category.PRODUCE),
                new Product("P100", "pear", "local", Category.PRODUCE),
                new Product("L103", "lemon", "local", Category.PRODUCE),
                new Product("M201", "milk", "farm", Category.DAIRY),
                new Product("Y001", "yogurt", "farm", Category.DAIRY),
                new Product("C333", "cheese", "farm", Category.DAIRY),
                new Product("R777", "rice chex", "Nabisco", Category.CEREAL),
                new Product("G111", "granola", "Nat Valley", Category.CEREAL),
                new Product("BB11", "ground beef", "butcher", Category.MEAT),
                new Product("CC11", "chicken", "butcher", Category.MEAT),
                new Product("BC11", "bacon", "butcher", Category.MEAT),
                new Product("BC77", "coke", "coca cola", Category.BEVERAGE),
                new Product("BC88", "coffee", "value", Category.BEVERAGE),
                new Product("BC99", "tea", "herbal", Category.BEVERAGE)
        );
        products.forEach(p -> inventory.put(p.sku(), new InventoryItem(p, 1000, 5, random.nextDouble(0, 1.25))));
    }

    void stockAisles() {
        aisleInventory = new EnumMap<>(Category.class);
        for (InventoryItem item : inventory.values()) {
            Category aisle = item.getProduct().category();
            Map<String, InventoryItem> productMap = aisleInventory.get(aisle);
            if (productMap == null) {
                productMap = new HashMap<>();
            }
            productMap.put(item.getProduct().name(), item);
            aisleInventory.putIfAbsent(aisle, productMap);
        }
    }

    void listInventory() {
        System.out.println("--------------------------------");
        inventory.values().forEach(System.out::println);
    }
}
