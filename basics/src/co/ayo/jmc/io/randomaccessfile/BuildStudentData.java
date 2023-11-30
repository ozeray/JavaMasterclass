package co.ayo.jmc.io.randomaccessfile;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BuildStudentData {

    public static void build(String datFileName) {
        Path studentJson = Path.of("basics/files/students.json");
        String dataFile = "basics/files/" + datFileName + ".dat";
        Map<Long, Long> indexedId = new LinkedHashMap<>(); // Store indexes in insertion order

        try {
            Files.deleteIfExists(Path.of(dataFile));
            String data = Files.readString(studentJson);
            data = data.replaceFirst("^(\\[)", "")
                    .replaceFirst("(\\])$", "");
            var records = data.split("[^\t]}, \\{");
            System.out.println("# of records: " + records.length);

            // int recordCount = 4 bytes; long student ID = 8 bytes, long record ID = 8 bytes => 16 bytes...
            long startingPos = 4 + (16L * records.length);
            // Before startingPos location, index data will be stored...

            Pattern idPattern = Pattern.compile("studentId\": ([0-9]+)");

            try (RandomAccessFile ra = new RandomAccessFile(dataFile, "rw")) {
                ra.seek(startingPos);
                for (String record : records) {
                    Matcher m = idPattern.matcher(record);
                    if (m.find()) {
                        long id = Long.parseLong(m.group(1));
                        indexedId.put(id, ra.getFilePointer());
                        ra.writeUTF(record);
                    }
                }
                writeIndex(ra, indexedId);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeIndex(RandomAccessFile ra, Map<Long, Long> indexMap) {
        try {
            ra.seek(0); // Always start from the beginning of the file
            ra.writeInt(indexMap.size()); // Write the number of records
            for (var studentIdx : indexMap.entrySet()) {
                ra.writeLong(studentIdx.getKey());
                ra.writeLong(studentIdx.getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
