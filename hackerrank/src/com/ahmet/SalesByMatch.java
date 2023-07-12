package com.ahmet;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SalesByMatch {

    public static void main(String[] args) {
        Integer[] arr = new Integer[]{1, 1, 3, 1, 2, 1, 3, 3, 3, 3};

        System.out.println(sockMerchant(arr.length, Arrays.asList(arr)));
    }

    public static int sockMerchant(int n, List<Integer> ar) {
        return ar.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                          .values().stream()
                                   .reduce(0L, (partialPairCount, value) -> partialPairCount += (value / 2))
                                   .intValue();
    }
}
