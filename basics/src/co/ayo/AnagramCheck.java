package co.ayo;

import java.util.Arrays;

public class AnagramCheck {

    public static void main(String[] args) {
        String str1 = "listen";
        String str2 = "silent";

        System.out.println("Anagram? " + isAnagram(str1, str2));
    }

    private static boolean isAnagram(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() != str2.length()) {
            return false;
        }
        char[] str1CharsSorted = str1.toCharArray();
        Arrays.sort(str1CharsSorted);

        char[] str2CharsSorted = str2.toCharArray();
        Arrays.sort(str2CharsSorted);

        return Arrays.equals(str1CharsSorted, str2CharsSorted);
    }
}
