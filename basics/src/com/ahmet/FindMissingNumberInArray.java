package com.ahmet;

public class FindMissingNumberInArray {

    public static void main(String[] args) {

        int[] arr = {1, 3, 4, 8, 5, 6, 7};
        System.out.println(new FindMissingNumberInArray().missingNum(arr));

        int[] arr2 = {3, 4, 2, 5};
        System.out.println(new FindMissingNumberInArray().missingNum(arr2));
    }

    private int missingNum(int[] arr) {
        int l = arr.length + 1;
        int sum = l * (l + 1) / 2;
        for (int e: arr) {
            sum -= e;
        }
        return sum;
    }
}
