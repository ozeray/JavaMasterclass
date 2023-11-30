package co.ayo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class DuplicateFinderInIntegerList {

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 2, 4, 3, 5, 6, 5, 7, 8, 8, 9};
        System.out.println(duplicates(array));
    }

    private static List<Integer> duplicates(int[] numbers) {
        Map<Integer, AtomicInteger> map = new HashMap<>();
        for (int i: numbers) {
            if (map.containsKey(i)) {
                map.get(i).incrementAndGet();
            } else {
                map.put(i, new AtomicInteger(1));
            }
        }
        return map.keySet().stream().filter(k -> map.get(k).get() > 1).toList();
    }
}
