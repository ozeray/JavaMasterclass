package tr.com.ahmet;

import java.io.*;
import java.util.List;
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
        return 1;
    }
}
