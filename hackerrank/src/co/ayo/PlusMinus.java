package co.ayo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class PlusMinus {

    @SuppressWarnings("unused")
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> intList = PlusMinusUtils.stringToIntegerList(bufferedReader.readLine());

        Result.plusMinus(intList);

        bufferedReader.close();
    }
}

class PlusMinusUtils {

    static List<Integer> stringToIntegerList(String str) {
        String[] strArr = str.replaceAll("\\s+$", "").split(" ");
        Stream<String> stringStream = Stream.of(strArr);
        return stringStream.map(Integer::parseInt).collect(toList());
    }
}

class PlusMinusWithScanner {

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine().trim());

        List<Integer> intList = PlusMinusUtils.stringToIntegerList(scanner.nextLine());

        Result.plusMinus(intList);

        scanner.close();
    }
}

class Result {

    public static void plusMinus(List<Integer> list) {
        double size = list.size();
        
        double posRate = list.stream().filter(i -> i > 0).count() / size;
        double negRate = list.stream().filter(i -> i < 0).count() / size;
        double zeroRate = list.stream().filter(i -> i == 0).count() / size;

        System.out.println(doubleToSixDecimals(posRate));
        System.out.println(doubleToSixDecimals(negRate));
        System.out.println(doubleToSixDecimals(zeroRate));
    }

    private static BigDecimal doubleToSixDecimals(double val) {
        return new BigDecimal(val).setScale(6, RoundingMode.HALF_UP);
    }
}