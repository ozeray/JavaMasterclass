package com.ahmet;

public class FindMinInArray {

    public static void main(String[] args) {
        int[] arr = {12, 1345, 35, 2, 63, 0, -7, 12};
        System.out.println(new FindMinInArray().min(arr));
    }

    private int min(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array must have elements");
        }
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (min > arr[i]) {
                min = arr[i];
            }
        }
        return min;

        /*
            5
            2
            1
            3n
            3(n-1)
            4n
            3n
            1
            ---> 13n + 6 => O(n)
         */
    }
}
