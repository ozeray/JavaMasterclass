package com.ahmet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MinMaxSum {

    public static void main(String[] args) throws java.io.IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<Integer> numbers = Stream.of(br.readLine()
                                            .replaceAll("\\s+$", "")
                                            .split(" "))
                                      .map(Integer::parseInt)
                                      .collect(Collectors.toList());
        miniMaxSum(numbers);
        br.close();
    }

    private static void miniMaxSum(List<Integer> numbers) {
        long grandSum = numbers.stream().map(Long::new).reduce(0L, Long::sum);
        long maxSum = 0, minSum = grandSum;

        for (Integer number : numbers) {
            long intermediateSum = grandSum - number;
            if (intermediateSum > maxSum) {
                maxSum = intermediateSum;
            }
            if (intermediateSum < minSum) {
                minSum = intermediateSum;
            }
        }

        System.out.println(minSum + " " + maxSum);
    }
}