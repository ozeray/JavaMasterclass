package com.ahmet.jmc;

import java.util.Scanner;

public class MinimumElementInArray {

    public static void main(String[] args) {
        int[] array = getIntegers(5);
        System.out.println("Minimum element: " + findMin(array));
    }

    private static int[] getIntegers(int capacity) {
        Scanner scanner = new Scanner(System.in);
        int[] array = new int[capacity];
        System.out.println("Enter " + capacity + " integer values:\r");
        for (int i = 0; i < array.length; i++) {
            array[i] = scanner.nextInt();
        }
        scanner.close();
        return array;
    }

    private static int findMin(int[] array) {
        int min = Integer.MAX_VALUE;
        for (int j : array) {
            if (j < min) {
                min = j;
            }
        }
        return min;
    }
}
