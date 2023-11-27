package com.ahmet.completejavadeveloper.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadingFiles {
    public static void main(String[] args) {
//        try(FileReader reader = new FileReader("basics/files/file.txt")) {
        try(FileReader reader = new FileReader("basics/files/file.txt")) {
            char[] block = new char[1000];
            int data;
            while ((data = reader.read(block)) != -1) {
                String content = new String(block, 0, data);
                System.out.printf("[%d chars] %s%n", data, content);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("----------------------------------------------------------------");
        try(BufferedReader br = new BufferedReader(new FileReader("basics/files/file.txt"))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                System.out.println(line);
//            }
            // Easier, after JDK 1.8:
            br.lines().forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
