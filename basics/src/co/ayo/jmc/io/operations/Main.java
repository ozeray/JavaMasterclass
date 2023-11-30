package co.ayo.jmc.io.operations;

import java.io.*;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class Main {

    public static void main(String[] args) {
//        File file = new File("basics/files/json.txt");
//        File fileNew = new File("basics/files/students.json");
////        if (file.exists()) {
//            file.renameTo(fileNew);
//            System.out.println("Renamed");
//        } else {
//            System.out.println("No file to rename");
//        }

//        Path oldPath = file.toPath();
//        Path newPath = fileNew.toPath();
//        try {
//            Files.move(oldPath, newPath);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        Path oldPath = Path.of("basics/files/students.json");
//        Path newPath = Path.of("basics/files/demo/json/students.json");
//        try {
//            Files.createDirectories(newPath.subpath(0, newPath.getNameCount() - 1));
//            Files.move(oldPath, newPath);
//            System.out.println("Renamed (moved)");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        Path filesDir = Path.of("basics/resources2");
//        Path resourcesDir = Path.of("basics/files");
//        try {
//            Files.move(filesDir, resourcesDir);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        Path filesDir = Path.of("basics/files/demo");
//        Path copyDir = Path.of("basics/copy");
//        try {
//            Files.copy(filesDir, copyDir);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        Path filesDir = Path.of("basics/files");
//        Path copyDir = Path.of("basics/filesCopy");
//        if (Files.exists(copyDir)) {
//            Files.delete(copyDir);
//        }
//        Files.deleteIfExists(copyDir);
//        recursiveDelete(copyDir);
//        recursiveCopy(filesDir, copyDir);

//        try(BufferedReader reader = new BufferedReader(new FileReader("basics/files/students.json"))) {
//            Path target = Path.of("basics/files/demo/students.json");
//            Files.createDirectories(target.subpath(0, target.getNameCount() - 1));
//            PrintWriter writer = new PrintWriter(target.toFile());
//            reader.transferTo(writer);
//        }

        String urlPopulation = "https://api.census.gov/data/2019/pep/charagegroups?get=NAME,POP&for=state:*";
        URI uri = URI.create(urlPopulation);
//        try (var urlInputStream = uri.toURL().openStream()) {
//            urlInputStream.transferTo(System.out);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        Path jsonPath = Path.of("basics/files/USPopulationByState.txt");
//        try (var inputStream = uri.toURL().openStream();
//             var reader = new InputStreamReader(inputStream);
//             var writer = Files.newBufferedWriter(jsonPath)) {
//            System.out.println(inputStream.getClass().getSimpleName());
//            System.out.println(reader.getClass().getSimpleName());
//            System.out.println(writer.getClass().getSimpleName());
//            reader.transferTo(writer);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        Path jsonPath = Path.of("basics/files/USPopulationByState.csv");
        try (var reader = new InputStreamReader(uri.toURL().openStream());
             var writer = Files.newBufferedWriter(jsonPath)) {
            Writer csvWriter = new Writer() {
                @Override
                public void write(char[] cbuf, int off, int len) throws IOException {
                    String jsonString = new String(cbuf, off, len);
                    jsonString = jsonString.replace("[", "");
                    jsonString = jsonString.replaceAll("\\]", "");
                    writer.write(jsonString);
                }

                @Override
                public void flush() throws IOException {
                    writer.flush();
                }

                @Override
                public void close() throws IOException {
                    writer.close();
                }
            };
            reader.transferTo(csvWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void recursiveCopy(Path source, Path target) throws IOException {
        Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING); // Shallow copy
        if (Files.isDirectory(source)) {
            try(var children = Files.list(source)) {
                children.toList().forEach(path -> {
                    try {
                        // Create mirrored directory structure:
                        recursiveCopy(path, target.resolve(path.getFileName()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
        }
    }

    private static void recursiveDelete(Path target) throws IOException {
        if (Files.isDirectory(target)) {
            try(var children = Files.list(target)) {
                children.toList().forEach(path -> {
                    try {
                        recursiveDelete(path);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
        }
        Files.delete(target);
    }
}
