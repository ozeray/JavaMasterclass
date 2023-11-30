package com.ahmet.jmc.collections.maps;

import java.util.*;

public class HashMapDemo {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.putIfAbsent("Ahmet", "Özer");
        map.put("Eren", "Özer");
        map.put("Kerem", "Özer");
        map.merge("Ahmet", "Yaşar", (oldValue, newValue) -> newValue.concat(" ").concat(oldValue));
        map.merge("Eren", "Said", (oldValue, newValue) -> newValue.concat(" ").concat(oldValue));
        map.replace("Kerem", "Saki Özer");
        map.computeIfAbsent("Arif", String::toUpperCase);
        map.put("Gülistan", "Özer");
        map.computeIfPresent("Gülistan", (key, oldValue) -> oldValue.toUpperCase());

        System.out.println(map);

        System.out.println(map.getOrDefault("Nagihan", "Kızılşar"));

        map.replaceAll((key, value) -> value.toUpperCase());
        System.out.println("-".repeat(30));
        System.out.println("After replaceAll");
        System.out.println(map);

        map.replace("Ahmet", "Y. Özer");
        System.out.println("-".repeat(30));
        System.out.println("After replacell");
        System.out.println(map);

        map.remove("Ahmet");
        System.out.println("-".repeat(30));
        System.out.println("After remove");
        System.out.println(map);

        Set<String> keysView = map.keySet();
        System.out.println("-".repeat(30));
        System.out.println("Key set:");
        System.out.println(keysView);

        TreeSet<String> copyOfKeys = new TreeSet<>(keysView);
        System.out.println("-".repeat(30));
        System.out.println("Key copies in alphabetical order:");
        System.out.println(copyOfKeys);

        System.out.println("-".repeat(30));
        System.out.println("Key Kerem exists in key set?");
        System.out.println(map.containsKey("Kerem"));

        keysView.remove("Arif");
        System.out.println("-".repeat(30));
        System.out.println("Key Arif removed from key set => Entry removed from the map:");
        System.out.println(map.containsKey("Arif"));
        System.out.println(keysView);
        System.out.println(copyOfKeys); // Not removed from the copied set
        System.out.println(map);


        keysView.retainAll(List.of("Eren", "Kerem"));
        System.out.println("-".repeat(30));
        System.out.println("Retain only the desired keys:");
        System.out.println(map);

        map.put("Ali", "Ziya ÖZER");
        System.out.println("-".repeat(30));
        System.out.println("Map changes are propagated to key set also:");
        System.out.println(keysView);

        // keysView.add("Lütfi"); throws UnsupportedOperationException!
//        System.out.println("-".repeat(30));
//        System.out.println("Add or addAll in key set DOES NOT get propagated to map:");
//        System.out.println(keysView);
//        System.out.println(map);

//        keysView.clear();
//        System.out.println("-".repeat(30));
//        System.out.println("Remove, removeAll, retainAll and clear in key set DO NOT get propagated to map:");
//        System.out.println(keysView);
//        System.out.println(map);

//        Collection<String> valuesView = map.values();
//        valuesView.remove("Ziya ÖZER");
//        System.out.println("-".repeat(30));
//        System.out.println("Remove, removeAll, retainAll and clear in values collection ALSO get propagated to map:");
//        System.out.println(keysView);
//        System.out.println(valuesView);

        System.out.println("-".repeat(30));
        System.out.println("Remove, removeAll, retainAll and clear in entry set ALSO get propagated to map:");
        Set<Map.Entry<String, String>> entriesView = map.entrySet();
        for (Map.Entry<String, String> node : entriesView) {
            System.out.println(node.getKey());
            if (node.getKey().equals("Ali")) {
                entriesView.remove(node);
            }
        }
        System.out.println(map);


        System.out.println("-".repeat(30));
        System.out.println("Sort the map:");
    }
}
