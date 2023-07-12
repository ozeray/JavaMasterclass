package com.ahmet.completejavadevelopercourse;

public class SharedDigit {
    public static void main(String[] args) {
        System.out.println(hasSharedDigit(12, 23));
        System.out.println(hasSharedDigit(9, 99));
        System.out.println(hasSharedDigit(15, 55));
    }

    public static boolean hasSharedDigit(int num1, int num2) {
        if (num1 < 10 || num1 > 99 || num2 < 10 || num2 > 99) {
            return false;
        }

        return num1 % 10 == num2 % 10 ||
               num1 % 10 == num2 / 10 ||
               num1 / 10 == num2 % 10 ||
               num1 / 10 == num2 / 10;
    }
}
