package co.ayo.jmc.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.time.Instant;

public class PathDemo {

    public static void main(String[] args) {
        Path path = Path.of("basics/files/testing.txt");
//        pathInfo(path);
        logStatement(path);
        extraInfo(path);
    }

    private static void pathInfo(Path path) {
        System.out.println(path);
        System.out.println(path.getFileName());
        System.out.println(path.getParent());
        Path absolutePath = path.toAbsolutePath();
        System.out.println(absolutePath);
        System.out.println(path.getRoot());
        System.out.println(absolutePath.getRoot());
        System.out.println(path.isAbsolute());
        System.out.println("-------------------------------");

        System.out.println(absolutePath.getRoot());
//        Iterator<Path> iterator = absolutePath.iterator();
        for (int i = 0; i < absolutePath.getNameCount(); i++) {
            System.out.println(".".repeat(i + 1) + " " + absolutePath.getName(i));
        }
        System.out.println("--------------------------------");
    }

    private static void logStatement(Path path) {
        try {
            Path parent = path.getParent();
            if (!Files.exists(parent)) {
                Files.createDirectory(parent);
//                Files.createDirectories(parent); Create in-between directories as well..
            }
            Files.writeString(path, Instant.now() + ": hello file world\n", StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void extraInfo(Path path) {
        try {
            var attributes = Files.readAttributes(path, "*");
            attributes.entrySet().forEach(System.out::println);
            System.out.println(Files.probeContentType(path));
            System.out.println(Files.readAttributes(path, "posix:*"));
            System.out.println(Files.getFileAttributeView(path, BasicFileAttributeView.class).readAttributes());
            System.out.println(Files.getFileAttributeView(path, FileOwnerAttributeView.class).getOwner());
        } catch (IOException e) {
            System.out.println("Problem getting attributes");;
        }
    }
}
