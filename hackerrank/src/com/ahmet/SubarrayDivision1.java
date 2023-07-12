package com.ahmet;

import java.io.*;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class SubarrayDivision1 {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getProperty("user.home") + "/subarray_division1.txt", true));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> s = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Integer::parseInt)
                .collect(toList());

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int d = Integer.parseInt(firstMultipleInput[0]);

        int m = Integer.parseInt(firstMultipleInput[1]);

        int result = birthday(s, d, m);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }

    /**
     * 1 <= s.size() <= 100
     * 1 <= s.get(i) <= 5
     * 1 <= d <= 31
     * 1 <= m <= 12
     */
    private static int birthday(List<Integer> s, int d, int m) {
        int count = 0;
        for (int i = 0; i < (s.size() - m + 1); i++) {
            int day = 0;
            for (int j = 0; j < m; j++) {
                day += s.get(i + j);
            }
            if (day == d) {
                count++;
            }
        }
        return count;
    }
}
