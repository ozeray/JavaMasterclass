package com.ahmet;

public class PalindromeStringCheck {

    public static void main(String[] args) {
        String s = "madam";
        System.out.println(new PalindromeStringCheck().isPalindromeString(s));

        s = "amadam";
        System.out.println(new PalindromeStringCheck().isPalindromeString(s));

        s = "maam";
        System.out.println(new PalindromeStringCheck().isPalindromeString(s));

        s = "mm";
        System.out.println(new PalindromeStringCheck().isPalindromeString(s));

        s = "m";
        System.out.println(new PalindromeStringCheck().isPalindromeString(s));
    }

    private boolean isPalindromeString(String text) {
        char[] arr = text.toCharArray();
        for (int start = 0, end = arr.length - 1; start < end; start++, end--) {
            if (arr[start] != arr[end]) {
                return false;
            }
        }
        return true;
    }
}
