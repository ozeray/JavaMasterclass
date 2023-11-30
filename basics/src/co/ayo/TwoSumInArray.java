package co.ayo;

import java.util.Arrays;

public class TwoSumInArray {

    public static void main(String[] args) {
        int[] numbers = {2, 7, 11, 18};
        int sum = 9;

        int[] result = twoSum(numbers, sum);

        System.out.println(Arrays.toString(result));
    }

    private static int[] twoSum(int[] arr, int sum) {
        for (int first : arr) {
            int foundAt = Arrays.binarySearch(arr, sum - first);
            if (foundAt != -1) {
                return new int[]{first, arr[foundAt]};
            }
        }
        return null;
    }
}
