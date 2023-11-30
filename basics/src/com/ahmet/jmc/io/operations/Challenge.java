package com.ahmet.jmc.io.operations;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.StringJoiner;

public class Challenge {

    public static void main(String[] args) {
        Path path = Path.of("basics");
//        for (String dir : new String[]{"public", "assets", "icons"}) {
//            path = path.resolve(dir);
//            try {
//                Files.createDirectory(path);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

//        Path child = path.resolve("public")
//                .resolve("assets")
//                .resolve("icons");

        Path child = Path.of("basics","public", "assets", "icons");
        try {
            Files.createDirectories(child);
        } catch (IOException e) {
            e.printStackTrace();
        }

        createIndexFiles(path, child, new StringJoiner(""));
    }

    private static void createIndexFiles(Path root, Path path, StringJoiner previousContent) {
        if (path.equals(root)) {
            return;
        }

        try {
            Path filePath = path.resolve("index.txt");
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
                System.out.println("Created index.txt file");
            } else {
                System.out.println("File already exists");
            }

            try (var contents = Files.list(path)) {
                contents.forEach(p -> {
                    try {
                        String absolutePath = p.toString();
                        Instant creationInstant = ((FileTime) Files.getAttribute(p, "creationTime")).toInstant();
                        LocalDateTime createTime = LocalDateTime.ofInstant(creationInstant, ZoneId.systemDefault());
                        previousContent.add("%-70s %2$tF %2$tT%n".formatted(absolutePath, createTime));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
                Files.writeString(filePath, previousContent.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        createIndexFiles(root, path.getParent(), previousContent);
    }
}
