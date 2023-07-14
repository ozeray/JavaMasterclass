package com.ahmet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WriteReadFile {

    public static void main(String[] args) throws IOException {
        Path path = Files.writeString(Files.createTempFile("demo", "txt"), "Sample text");
        System.out.println(path);
        System.out.println(Files.readString(path));
    }
}
