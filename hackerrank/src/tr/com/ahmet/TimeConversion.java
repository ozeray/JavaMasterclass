package tr.com.ahmet;

import java.io.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeConversion {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bw = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        BufferedWriter bw = new BufferedWriter(new FileWriter(System.getProperty("user.home") + "/time_conversion.txt", true));

        String time = br.readLine();
        String militaryTime = amPmToMilitary(time);

        bw.write(militaryTime);
        bw.newLine();

        br.close();
        bw.close();

//        System.out.println(amPmToMilitary("07:05:45AM"));
    }

    private static String amPmToMilitary(String time) {
        return LocalTime.parse(time, DateTimeFormatter.ofPattern("hh:mm:ssa"))
                        .format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }
}
