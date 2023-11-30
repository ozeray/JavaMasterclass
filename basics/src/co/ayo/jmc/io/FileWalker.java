package co.ayo.jmc.io;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class FileWalker {

    public static void main(String[] args) {
        Path startingPath = Path.of("/media/ahmet/Depo");
//        FileVisitor<Path> statsVisitor = new StatsVisitor();
//        FileVisitor<Path> cumulativeSizeVisitor = new CumulativeSizeVisitor(3);
        FileVisitor<Path> cumulativeSizeVisitor = new CumulativeSizeAndCountVisitor(2);
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
        private final Map<Path, Long> folderSizes = new LinkedHashMap<>();
        private int initialCount;
        private final int printLevel;

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
            }

            return FileVisitResult.CONTINUE;
        }
    }

    private static class CumulativeSizeAndCountVisitor extends SimpleFileVisitor<Path> {
        private Path initialPath = null;
        private final Map<Path, Map<String, Long>> folderSizesAndCounts = new LinkedHashMap<>();
        private int initialCount;
        private final int printLevel;

        private static final String FILE_SIZE = "size";
        private static final String FILE_CNT = "files";
        private static final String DIR_CNT = "folders";

        public CumulativeSizeAndCountVisitor(int printLevel) {
            this.printLevel = printLevel;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            Objects.requireNonNull(file);
            Objects.requireNonNull(attrs);

            var parentValues = folderSizesAndCounts.get(file.getParent());
            if (parentValues != null) {
                parentValues.merge(FILE_SIZE, 0L, (oldSize, newSize) -> oldSize + attrs.size());
                parentValues.merge(FILE_CNT, 0L, (oldCount, newCount) -> oldCount + 1);
                //OR: parentValues.merge(FILE_CNT, 1L, Math::addExact);
            }

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
                    folderSizesAndCounts.clear();
                }
                HashMap<String, Long> valuesMap = new LinkedHashMap<>();
                valuesMap.put(FILE_SIZE, 0L);
                valuesMap.put(FILE_CNT, 0L);
                valuesMap.put(DIR_CNT, 0L);
                folderSizesAndCounts.put(dir, valuesMap);
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
                folderSizesAndCounts.forEach((key, valuesMap) -> {
                    Long size = valuesMap.get(FILE_SIZE);
                    Long files = valuesMap.get(FILE_CNT);
                    Long folders = valuesMap.get(DIR_CNT);
                    int level = key.getNameCount() - initialCount - 1;
                    if (level < printLevel) {
                        System.out.printf("%s %s - [%d bytes] (Files: %d, Folders: %d)%n", "\t".repeat(level),
                                key.getFileName(), size, files, folders);
                    }
                });
            } else {
                var parentValues = folderSizesAndCounts.get(dir.getParent());
                var currentValues = folderSizesAndCounts.get(dir);
                parentValues.merge(FILE_SIZE, 0L, (oldSize, newSize) -> oldSize + currentValues.get("size"));
                parentValues.merge(FILE_CNT, 0L, (oldCount, newCount) -> oldCount + currentValues.get("files"));
                parentValues.merge(DIR_CNT, 0L, (oldCount, newCount) -> oldCount + currentValues.get("folders") + 1);
            }

            return FileVisitResult.CONTINUE;
        }
    }
}
