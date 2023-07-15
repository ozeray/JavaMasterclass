package com.ahmet;

import java.io.*;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class LonelyInteger {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new FileWriter(System.getProperty("user.home") + "/lonely_integer.txt", true));

        List<Integer> numbers = Stream.of(br.readLine()
                                            .replaceAll("\\s+$", "")
                                            .split(" "))
                                       .map(Integer::parseInt)
                                       .collect(toList());

        int result = lonelyinteger(numbers);
        bw.write(String.valueOf(result));

        br.close();
        bw.close();
    }

    private static int lonelyinteger(List<Integer> numbers) {
        int lonely = 0;
        for (Integer num : numbers) {
            if (numbers.stream().filter(i -> Objects.equals(i, num)).count() == 1) {
                lonely = num;
                break;
            }
        }
        return lonely;
    }
}
