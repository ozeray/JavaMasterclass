package com.ahmet;

import java.util.*;

public class DuplicatesFinderInString {

    public static void main(String[] args) {
        System.out.println(findDuplicatesUsingForLoop("code decode"));
        System.out.println(findDuplicatesUsingCharsArray("code decode"));
        System.out.println(findDuplicatesUsingMap("code decode"));
    }

    private static Set<Character> findDuplicatesUsingForLoop(String input) { // Time complexity: O(n^2)
        Set<Character> duplicates = new LinkedHashSet<>(); // If ordered is wanted
        // Space complexity: O(K), where K <= size of input / 2

        for (int i = 0; i < input.length(); i++) { // O(n)
            for (int j = i + 1; j < input.length(); j++) { // O(n)
                if (input.charAt(i) == input.charAt(j)) {
                    duplicates.add(input.charAt(i)); // O(1)
                }
            }
        }
        return duplicates;
    }

    private static Set<Character> findDuplicatesUsingCharsArray(String input) { // Time complexity: O(n) + O(n) = O(2n) =~ O(n). Result is alphabetically ordered, due to use of chars array
        Set<Character> duplicates = new HashSet<>();

        int[] chars = new int[256]; // Space complexity: O(256)

        for (int i = 0; i < input.length(); i++) { // O(n)
            char c = input.charAt(i);
            chars[c] += 1;
        }

        for (int i = 0; i < chars.length; i++) { // O(n)
            if (chars[i] > 1) {
                duplicates.add((char) i);
            }
        }

        return duplicates;
    }

    private static Set<Character> findDuplicatesUsingMap(String input) { // Time complexity: O(nlogn).
        // Result is alphabetically ordered, due to use of chars map, where the sorting done via char's hashcode, resulting in natural order due to hash codes...
        // Space complexity: O(K), where K <= size of input

        Map<Character, Integer> countMap = new HashMap<>();
        Set<Character> duplicates = new HashSet<>();

        for (int i = 0; i < input.length(); i++) { // O(n)
            char c = input.charAt(i);
            if (countMap.containsKey(c)) {
                countMap.put(c, countMap.get(c) + 1); // O(logn) (worst case)
            } else {
                countMap.put(c, 1); // O(1)
            }
        }

        for (Map.Entry<Character, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() > 1) {
                duplicates.add(entry.getKey());
            }
        }

        return duplicates;
    }

}
