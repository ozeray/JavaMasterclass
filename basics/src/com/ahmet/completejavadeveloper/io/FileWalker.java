package com.ahmet.completejavadeveloper.io;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class FileWalker {

    public static void main(String[] args) {
        Path startingPath = Path.of("..");
//        FileVisitor<Path> statsVisitor = new StatsVisitor();
        FileVisitor<Path> cumulativeSizeVisitor = new CumulativeSizeVisitor(3);
        try {
            Files.walkFileTree(startingPath, cumulativeSizeVisitor);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static class StatsVisitor extends SimpleFileVisitor<Path> {
        private int level;

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            Objects.requireNonNull(file);
            Objects.requireNonNull(attrs);
            System.out.println("\t".repeat(level + 1) + file.getFileName());
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            Objects.requireNonNull(dir);
            Objects.requireNonNull(attrs);
            level++;
            System.out.println("\t".repeat(level) + dir.getFileName());
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
            Objects.requireNonNull(dir);
//            if (exc != null)
//                throw exc;
            level--;
            return FileVisitResult.CONTINUE;
        }
    }

    private static class CumulativeSizeVisitor extends SimpleFileVisitor<Path> {
        private Path initialPath = null;
        private Map<Path, Long> folderSizes = new LinkedHashMap<>();
        private int initialCount;
        private int printLevel;

        public CumulativeSizeVisitor(int printLevel) {
            this.printLevel = printLevel;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            Objects.requireNonNull(file);
            Objects.requireNonNull(attrs);

            folderSizes.merge(file.getParent(), 0L, (oldSize, newSize) -> oldSize + attrs.size());

            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            Objects.requireNonNull(dir);
            Objects.requireNonNull(attrs);

            if (initialPath == null) {
                initialPath = dir;
                initialCount = dir.getNameCount();
            } else {
                int relativeLevel = dir.getNameCount() - initialCount;
                if (relativeLevel == 1) {
                    folderSizes.clear();
                }
                folderSizes.put(dir, 0L);
            }

            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
            Objects.requireNonNull(dir);
//            if (exc != null)
//                throw exc;

            if (dir.equals(initialPath)) {
                return FileVisitResult.TERMINATE; // or CONTINUE, doesn't matter
            }
            int relativeLevel = dir.getNameCount() - initialCount;
            if (relativeLevel == 1) {
                folderSizes.forEach((key, value) -> {
                    int level = key.getNameCount() - initialCount - 1;
                    if (level < printLevel) {
                        System.out.printf("%s %s - [%d bytes]%n", "\t".repeat(level),
                                key.getFileName(), value);
                    }
                });
            } else {
                folderSizes.merge(dir.getParent(), 0L, (oldSize, newSize) -> oldSize + folderSizes.get(dir));

//                System.out.printf("%s %s[%s]%n", "  ".repeat(relativeLevel + 1),
//                        dir.getParent().getFileName(), folderSizes.get(dir.getParent()));
            }
//            System.out.println("%s %s".formatted(" ".repeat(relativeLevel + 1), dir.getFileName()));

            return FileVisitResult.CONTINUE;
        }
    }
}
