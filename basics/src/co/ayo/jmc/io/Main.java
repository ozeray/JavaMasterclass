package co.ayo.jmc.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        System.out.println("Current directory (pwd) is: " +
                new File("").getAbsolutePath());
        for (File f : File.listRoots()) {
            System.out.println(f);
        }
        String fileName = "basics/files/test.csv";
//        String fileName = "files/test.csv";

//        Path path = Paths.get(fileName);
//        try {
//            Files.readAllLines(path);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        File file = new File("basics", fileName);
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("File not found");
            return;
        }
        System.out.println(file.getAbsolutePath());
        System.out.println("Good to go.");

        if (!Files.exists(Paths.get(fileName))) {
            System.out.println("2. File not found");
            return;
        }
        System.out.println("2. Good to go.");



        // LBYL: Look Before You Leap => Check for errors before operation
        // ETAFP: Easier To Ask for Forgiveness than Permission => Assume an operation will usually succeed, and handle
        // any errors that occur.


    }

    private static void testFile(String fileName) {
        try (FileReader fileReader = new FileReader(fileName)) {
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
