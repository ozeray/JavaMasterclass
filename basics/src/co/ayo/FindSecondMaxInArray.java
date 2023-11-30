package co.ayo;

public class FindSecondMaxInArray {

    public static final int MAX = 1000000000;

    public static void main(String[] args) {
        FindSecondMaxInArray findSecondMaxInArray = new FindSecondMaxInArray();

        int[] arr = {12, 65, 44, 67, 1, 1};
        System.out.println(findSecondMaxInArray.secondMax(arr));

        int[] arr2 = {102, 65, 44, 67, 1, 1};
        System.out.println(findSecondMaxInArray.secondMax(arr2));

        int[] arr3 = {102, 165, 44, 67, 1, 1};
        System.out.println(findSecondMaxInArray.secondMax(arr3));

        int[] arr4 = {202, 165};
        System.out.println(findSecondMaxInArray.secondMax(arr4));

        int[] arr6 = {2, 165, 4, 500, 12, 45};
        System.out.println(findSecondMaxInArray.secondMax(arr6));

        int[] arr7 = {2, 5, 4, 500, 1200, 45};
        System.out.println(findSecondMaxInArray.secondMax(arr7));


        arr7 = new int[MAX];
        for (int i = 0; i < MAX; i++) {
            arr7[i] = i;
        }

        long now = System.currentTimeMillis();
        findSecondMaxInArray.secondMax(arr7);
        System.out.println("Second Max 1: " + (System.currentTimeMillis() - now) + " msecs");

        //        arr7 = null;
        //        System.gc();

        arr7 = new int[MAX];
        for (int i = 0; i < MAX; i++) {
            arr7[i] = i;
        }

        now = System.currentTimeMillis();
        findSecondMaxInArray.secondMax2(arr7);
        System.out.println("Second Max 2: " + (System.currentTimeMillis() - now) + " msecs");

    }

    private int secondMax(int[] arr) {
        if (arr == null || arr.length < 2) {
            throw new IllegalArgumentException("Array must have at least 2 elements");
        }

        int max = arr[0], secondMax = arr[1];
        if (max < secondMax) {
            int tmp = secondMax;
            secondMax = max;
            max = tmp;
        }

        for (int i = 2; i < arr.length; i++) {
            if (arr[i] > max) {
                secondMax = max;
                max = arr[i];
            } else if (arr[i] > secondMax) {
                secondMax = arr[i];
            }
        }

        return secondMax;

        /*
            4
            3
            6
            1
            3(n-1) + 3(n-2)
            4(n-2)
            2(n-2)
            3(n-2)
            1
            --> 15n - 12 => O(n)
         */
    }

    private int secondMax2(int[] arr) {
        if (arr == null || arr.length < 2) {
            throw new IllegalArgumentException("Array must have at least 2 elements");
        }

        int max = Integer.MIN_VALUE, secondMax = Integer.MAX_VALUE;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                secondMax = max;
                max = arr[i];
            } else if (arr[i] > secondMax && arr[i] != max) {
                secondMax = arr[i];
            }
        }
        return secondMax;

        /*
            4
            1
            3(n+1) + 3(n)
            4n
            2n
            3n
            1
            --> 15n + 9 => O(n)
         */
    }
}
