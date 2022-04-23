package tr.com.ahmet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FlippingMatrix {

    public static void main(String[] args) {
        List<List<Integer>> matrix = new ArrayList<>();
//        matrix.add(new ArrayList<>(Arrays.asList(1, 4)));
//        matrix.add(new ArrayList<>(Arrays.asList(3, 2)));

        matrix.add(new ArrayList<>(Arrays.asList(112, 42, 83, 119)));
        matrix.add(new ArrayList<>(Arrays.asList(56, 125, 56, 49)));
        matrix.add(new ArrayList<>(Arrays.asList(15, 78, 101, 43)));
        matrix.add(new ArrayList<>(Arrays.asList(62, 98, 114, 108)));

        System.out.println(flippingMatrix(matrix));
    }

    public static int flippingMatrix(List<List<Integer>> matrix) {
        int n = matrix.size() / 2;

        int maxSum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                maxSum += Math.max(Math.max(matrix.get(i).get(j), matrix.get(i).get(2*n - j - 1)),
                                   Math.max(matrix.get(2*n - i - 1).get(j), matrix.get(2*n - i - 1).get(2 * n - j - 1)));
            }
        }
        return maxSum;
    }
}
