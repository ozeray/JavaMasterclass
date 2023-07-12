package com.ahmet;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.joining;

public class SparseArrays {

    public static void main(String[] args) throws java.io.IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new FileWriter(System.getProperty("user.home") + "/sparse_arrays.txt", true));

        int stringsCount = Integer.parseInt(br.readLine().trim());

        List<String> strings = IntStream.range(0, stringsCount)
                                        .mapToObj(i -> {
                                            try {
                                                return br.readLine();
                                            } catch (IOException e) {
                                                throw new RuntimeException(e);
                                            }
                                        })
                                        .collect(toList());

        int queriesCount = Integer.parseInt(br.readLine().trim());

        List<String> queries = IntStream.range(0, queriesCount)
                                        .mapToObj(i -> {
                                            try {
                                                return br.readLine();
                                            } catch (IOException e) {
                                                throw new RuntimeException(e);
                                            }
                                        })
                                        .collect(toList());

        List<Integer> result = matchingStrings(strings, queries);

        bw.write(result.stream()
                       .map(Object::toString)
                       .collect(joining("\n"))
                 + "\n");

        br.close();
        bw.close();

    }

    private static List<Integer> matchingStrings(List<String> strings, List<String> queries) {
        return queries.stream().map(q -> (int) strings.stream().filter(s -> s.equals(q)).count()).collect(Collectors.toList());
    }
}
