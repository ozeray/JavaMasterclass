package com.ahmet;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {
        String input = "abcabcbb";
        int length = longestSubstringLength(input);

        System.out.println(length);
    }

    private static int longestSubstringLength(String str) {
        int maxLength = 0;
        int left = 0;
        int right = 0;
        Set<Character> longest = new HashSet<>();
        while (right < str.length()) {
            char currentChar = str.charAt(right);

            if (longest.contains(currentChar)) {
                longest.remove(str.charAt(left));
                left++;
            } else {
                maxLength = Math.max(maxLength, right - left + 1);
                longest.add(currentChar);
                right++;
            }
        }
        return maxLength;
    }
}
