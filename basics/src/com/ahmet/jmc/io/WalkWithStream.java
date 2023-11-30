package com.ahmet.jmc.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.stream.Collectors;

public class WalkWithStream {
    public static void main(String[] args) {
        Path startingPath = Path.of("basics");
        int index = startingPath.getNameCount();
        try (var paths = Files.walk(startingPath, Integer.MAX_VALUE)) {
            paths.filter(Files::isRegularFile)
                 .collect(Collectors.groupingBy(p -> p.subpath(index, index + 1),
                        Collectors.summarizingLong(
                                p -> {
//                                  Clearer, but less performant: p.toFile().length()
                                    try {
                                        return Files.size(p);
                                    } catch (IOException e) {
                                        throw new RuntimeException(e);
                                    }
                                })))
                 .entrySet()
                 .stream()
                 .filter(e -> e.getValue().getSum() > 100)
                 .sorted(Comparator.comparing(e -> e.getKey().toString()))
                 .forEach(e ->
                         System.out.printf("[%s] %,d bytes, %d files%n", e.getKey(),
                                 e.getValue().getSum(), e.getValue().getCount()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
