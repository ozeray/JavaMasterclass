package tr.com.ahmet;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class DiagonalDifference {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new FileWriter(System.getProperty("user.home") + "/diagonal_difference.txt", true));

        int count = Integer.parseInt(br.readLine().trim());

        List<List<Integer>> arr = new ArrayList<>();

        IntStream.range(0, count).forEach(i -> {
            try {
                arr.add(Stream.of(br.readLine()
                                    .replaceAll("\\s+$", "")
                                    .split(" "))
                              .map(Integer::parseInt)
                              .collect(toList()));

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        int result = diagonalDifference(arr);
        bw.write(String.valueOf(result));
        bw.newLine();

        br.close();
        bw.close();
    }

    private static int diagonalDifference(List<List<Integer>> arr) {
        int leftToRightDiagonalSum = 0, rightToLeftDiagonalSum = 0;
        int size = arr.size();
        for (int i = 0; i < size; i++) {
            List<Integer> row = arr.get(i);
            leftToRightDiagonalSum += row.get(i);
            rightToLeftDiagonalSum += row.get(size - i - 1);
        }

        return Math.abs(leftToRightDiagonalSum - rightToLeftDiagonalSum);
    }
}
