package com.ahmet.jmc.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileDemo {

    public static void main(String[] args) {
        useFile("basics/files/test2.csv");
        usePath("basics/files/pathfile.csv");
    }

    private static void useFile(String fileName) {
        File file = new File(fileName);
        boolean exists = file.exists();

        System.out.printf("File '%s' %s%n", fileName, exists ? "exists" : "doesn't exist");

        if (exists) {
            exists = !file.delete();
        }
        if (!exists) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Something went wrong");
            }
            System.out.println("Created file " + fileName);
            if (file.canWrite()) {
                System.out.println("Write to file here");
            }
        }
    }

    private static void usePath(String fileName) {
        Path path = Path.of(fileName);
        boolean exists = Files.exists(path);

        System.out.printf("File '%s' %s%n", fileName, exists ? "exists" : "doesn't exist");

        if (exists) {
            try {
                Files.delete(path);
                exists = false;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (!exists) {
            try {
                Files.createFile(path);
                System.out.println("Created file " + fileName);
                if (Files.isWritable(path)) {
                    System.out.println("Write to file here");
                    Files.writeString(path, """
                            Here is some data,
                            For my file,
                            just to prove,
                            using Files class and path are better!
                            """);

                }
                System.out.println("---------------------");
                Files.readAllLines(path).forEach(System.out::println);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
