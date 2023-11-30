package co.ayo.jmc.io.writing;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class FileWritingChallenge {
    public static void printJson(List<Student> students) {
        try (PrintWriter writer = new PrintWriter("basics/files/json.txt")) {

            List<Map<String, Object>> studentsCollection = students.stream().map(student -> {
                long id = student.getStudentId();
                Map<String, Object> demographicsMap = new LinkedHashMap<>();
                demographicsMap.put("countryCode", student.getCountry());
                demographicsMap.put("enrolledMonth", student.getEnrollmentMonth());
                demographicsMap.put("enrolledYear", student.getEnrollmentYear());
                demographicsMap.put("ageAtEnrollment", student.getEnrollmentAge());
                demographicsMap.put("gender", student.getGender());
                demographicsMap.put("previousProgrammingExperience", student.hasExperience());

                Collection<Map<String, Object>> engagements = student.getEngagementMap().entrySet()
                        .stream().map(e -> {
                            CourseEngagement ce = e.getValue();
                            Map<String, Object> engagementMap = new LinkedHashMap<>();
                            engagementMap.put("courseCode", e.getKey());
                            engagementMap.put("engagementType", ce.getEngagementType());
                            engagementMap.put("enrollmentMonth", ce.getEnrollmentMonth());
                            engagementMap.put("enrollmentYear", ce.getEnrollmentYear());
                            return engagementMap;
                        }).toList();
                Map<String, Object> studentMap = new LinkedHashMap<>();
                studentMap.put("studentId", id);
                studentMap.put("demographics", demographicsMap);
                studentMap.put("engagement", engagements);
                return studentMap;
            }).toList();

            writeToJson(writer, studentsCollection);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void writeToJson(PrintWriter writer, Object value) {
        writeToJson(writer, value, 0, true);
    }

    private static void writeToJson(PrintWriter writer, Object value, int indentCount, boolean newLineAfterComma) {
        if (value instanceof Iterator<?> iterator) {
            while (iterator.hasNext()) {
                writeToJson(writer, iterator.next(), indentCount, newLineAfterComma);
                if (iterator.hasNext()) {
                    writer.append(",");
                }
                writer.append(newLineAfterComma ? System.lineSeparator() : iterator.hasNext() ? " " : "");
            }
        } else if (value instanceof Collection<?> collection) {
            writer.append("[");
            writeToJson(writer, collection.iterator(), indentCount, false);
            writer.append("]");
        } else if (value instanceof Map<?, ?> map) {
            writer.append("{").append(System.lineSeparator());
            writeToJson(writer, map.entrySet().iterator(), indentCount + 1, true);
            writer.format("%s}", "\t".repeat(indentCount));
        } else if (value instanceof Map.Entry<?, ?> entry) {
            writer.format("%s\"%s\": ", "\t".repeat(indentCount), entry.getKey());
            writeToJson(writer, entry.getValue(), indentCount, newLineAfterComma);
        } else {
            if (value instanceof String s) {
                writer.format("\"%s\"", s);
            } else if (value instanceof Character c) {
                writer.format("\"%c\"", c);
            } else {
                writer.append(value.toString());
            }
        }
    }
}
