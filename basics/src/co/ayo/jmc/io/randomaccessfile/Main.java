package co.ayo.jmc.io.randomaccessfile;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final Map<Long, Long> indexedIds = new LinkedHashMap<>();
    private static int recordsInFile = 0;

    public static void main(String[] args) {
//        BuildStudentData.build("studentData");
        try (RandomAccessFile ra = new RandomAccessFile("basics/files/studentData.dat", "r")) {
            loadIndex(ra, 0);

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a student ID or 0 to quit");
            while (scanner.hasNext()) {
                long studentId = Long.parseLong(scanner.nextLine());
                if (studentId < 1) {
                    break;
                }
                ra.seek(indexedIds.get(studentId));
                System.out.println(ra.readUTF());
                System.out.println("Enter another ID");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

//        try (RandomAccessFile ra = new RandomAccessFile("basics/files/studentData.dat", "rw")) {
//            loadIndex(ra, 0);
//            long id = 777L;
//            long recordFP = indexedIds.get(id);
//            ra.seek(recordFP);
//            String record = ra.readUTF();
//            System.out.println(record);
//            record = record.replace("countryCode\": \"US", "countryCode\": \"TR");
//            ra.seek(recordFP);
//            ra.writeUTF(record);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    private static void loadIndex(RandomAccessFile ra, int indexPosition) {
        try {
            ra.seek(0);
            recordsInFile = ra.readInt();
            System.out.println(recordsInFile);
            for (int i = 0; i < recordsInFile; i++) {
                indexedIds.put(ra.readLong(), ra.readLong());
            }
            System.out.println(indexedIds.entrySet());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
