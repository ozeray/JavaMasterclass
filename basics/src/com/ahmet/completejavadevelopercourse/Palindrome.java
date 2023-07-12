package com.ahmet.completejavadevelopercourse;

public class Palindrome {

    public static void main(String[] args) {
        System.out.println(isPalindrome(1221));
        System.out.println(isPalindrome(-1221));
        System.out.println(isPalindrome(-707));
        System.out.println(isPalindrome(2111));
    }

    public static boolean isPalindrome(int number) {
        int reverse = 0;
        int absoluteNumber = Math.abs(number);

        while (absoluteNumber > 0) {
            int leastSignificantDigit = absoluteNumber % 10;
            reverse *= 10;
            reverse += leastSignificantDigit;
            absoluteNumber /= 10;
        }

        return reverse == Math.abs(number);
    }
}
