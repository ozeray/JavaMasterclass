package co.ayo;

import java.util.Arrays;

public class MissingNumberInSequence {

    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12};
        System.out.println(missingNum(numbers));
    }

    private static int missingNum(int[] numbers) {
        int max = numbers[numbers.length - 1];
        int expectedSum = max * (max + 1) / 2;
        return expectedSum - Arrays.stream(numbers).sum();
        // OR: return expectedSum - IntStream.of(numbers).sum();
    }
}
