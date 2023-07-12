package com.ahmet;

import java.io.*;
import java.util.stream.IntStream;

public class Pangrams {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new FileWriter(System.getProperty("user.home") + "/pangrams.txt", true));

        String s = br.readLine();

        bw.write(pangrams(s));
        bw.newLine();

        br.close();
        bw.close();
    }

    private static String pangrams(String s) {
        boolean pangram = true;
        for (int ch : IntStream.rangeClosed('a', 'z').toArray()) {
            String letter = String.valueOf((char) ch);
            if (!s.contains(letter) && !s.contains(letter.toUpperCase())) {
                pangram = false;
                break;
            }
        }
        return pangram ? "pangram" : "not pangram";
    }
}
