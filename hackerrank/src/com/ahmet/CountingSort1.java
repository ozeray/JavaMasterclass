package com.ahmet;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CountingSort1 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new FileWriter(System.getProperty("user.home") + "/counting_sort1.txt", true));

        int n = Integer.parseInt(br.readLine().trim());

        List<Integer> arr = Stream.of(br.readLine()
                                        .replaceAll("\\s+$", "")
                                        .split(" "))
                                  .map(Integer::parseInt)
                                  .collect(Collectors.toList());

        List<Integer> result = countingSort(arr);

        bw.write(result.stream()
                           .map(Object::toString)
                           .collect(Collectors.joining(" "))
                     + "\n");

        br.close();
        bw.close();
    }

    private static List<Integer> countingSort(List<Integer> arr) {
        List<Integer> freqArray = new ArrayList<>(Collections.nCopies(100, 0));

        IntStream.range(0, 100)
                 .forEach(i -> freqArray.set(i, (int) arr.stream().filter(n -> n == i).count()));

        return freqArray;
    }
}
