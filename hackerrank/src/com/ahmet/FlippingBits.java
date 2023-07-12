package com.ahmet;

import java.io.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FlippingBits {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new FileWriter(System.getProperty("user.home") + "/flipping_bits.txt", true));

        int count = Integer.parseInt(br.readLine().trim());

        IntStream.range(0, count).forEach(i -> {
            try {
                long num = Long.parseLong(br.readLine().trim());
                long flipped = flippingBits(num);

                bw.write(String.valueOf(flipped));
                bw.newLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        br.close();
        bw.close();
    }

    private static long flippingBits(long n) {
        String binary = String.format("%32s", Long.toBinaryString(n)).replaceAll(" ", "0");
        String flippedStr = binary.chars().mapToObj(i -> String.valueOf((i + 1) % 2)).collect(Collectors.joining());
        return Long.parseUnsignedLong(flippedStr, 2);
    }
}
