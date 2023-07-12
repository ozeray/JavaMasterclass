package com.ahmet;

public class StringReverser {

    public static void main(String[] args) {
        String orig = "original";

        String reversed = reverse(orig);
        System.out.println(reversed);

        reversed = manualReverse(orig);
        System.out.println(reversed);
    }

    private static String manualReverse(String orig) {
        String reverse = "";
        for (int i = 0; i < orig.length(); i++) {
            reverse = orig.charAt(i) + reverse;
        }
        return reverse;
    }

    private static String reverse(String orig) {
        StringBuilder sb = new StringBuilder(orig);
        return sb.reverse().toString();
    }
}
