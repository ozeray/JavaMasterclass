package co.ayo.jmc.io.writing;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        String header = """
                Student Id,Country Code,Enrolled Month,Enrolled Year,Age,Gender,\
                Experienced,Course Code,Engagement Month,Engagement Year,\
                Engagement Type""";

        Course jmc = new Course("JMC", "Java Masterclass");
        Course pymc = new Course("PYC", "Python Masterclass");
        List<Student> students = Stream
                .generate(() -> Student.getRandomStudent(jmc, pymc))
                .limit(1000)
                .toList();

        System.out.println(header);
        students.forEach(s -> s.getEngagementRecords().forEach(System.out::println));

        Path path = Path.of("basics/files/students.csv");
        try {
            Files.writeString(path, header);
            for (Student student : students) {
                Files.write(path, student.getEngagementRecords(), StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            List<String> data = new ArrayList<>();
            data.add(header);
            for (Student student : students) {
                data.addAll(student.getEngagementRecords());
            }
            Files.write(path, data);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer =
                     Files.newBufferedWriter(Path.of("basics/files/take2.csv"))) {
            writer.write(header);
            writer.newLine();
            for (Student student : students) {
                for (var record : student.getEngagementRecords()) {
                    writer.write(record);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileWriter writer = new FileWriter("basics/files/take3.csv")) {
            writer.write(header);
            for (Student student : students) {
                for (var record : student.getEngagementRecords()) {
                    writer.write(record);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (PrintWriter writer = new PrintWriter("basics/files/take4.csv")) {
            writer.write(header);
            for (Student student : students) {
                for (var record : student.getEngagementRecords()) {
                    writer.println(record);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (PrintWriter writer = new PrintWriter("basics/files/take4.txt")) {
            writer.println(header);

            for (Student student : students) {
                for (var record : student.getEngagementRecords()) {
                    String[] recordData = record.split(",");
                    writer.printf("%-12d%-5s%2d%4d%3d%-1s", student.getStudentId(),
                            student.getCountry(), student.getEnrollmentYear(),
                            student.getEnrollmentMonth(), student.getEnrollmentAge(),
                            student.getGender());
                    writer.printf("%-1s", student.hasExperience() ? 'Y' : 'N');
                    writer.format("%-3s%10.2f%-10s%-4s%-30s",
                            recordData[7], // Course code
                            student.getPercentComplete(recordData[7]),
                            recordData[8], // Engagement month
                            recordData[9], // Engagement year
                            recordData[10] // Engagement type
                            );
                    writer.println();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        System.out.println("------------------------------------------------");
        FileWritingChallenge.printJson(students);
    }
}
