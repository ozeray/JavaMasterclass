package co.ayo.jmc.datetime;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.format.TextStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {
//        System.out.println(LocalDate.now());
//
//        System.out.println(LocalDate.of(2022, 5, 5));
//        System.out.println(LocalDate.of(2022, Month.MAY, 5));
//        System.out.println(LocalDate.ofYearDay(2022, 125));
//        System.out.println(LocalDate.parse("2022-05-05"));
//
//        LocalDate may5 = LocalDate.parse("2022-05-05", DateTimeFormatter.ISO_DATE);
//        System.out.println(may5);
//        System.out.println(may5.getYear());
//        System.out.println(may5.getMonth());
//        System.out.println(may5.getMonthValue());
//        System.out.println(may5.getDayOfMonth());
//        System.out.println(may5.getDayOfWeek());
//        System.out.println(may5.getDayOfYear());
//        System.out.println(may5.get(ChronoField.YEAR));
//        System.out.println(may5.get(ChronoField.MONTH_OF_YEAR));
//        System.out.println(may5.get(ChronoField.DAY_OF_MONTH));
//        System.out.println(may5.get(ChronoField.DAY_OF_YEAR));
//
//        System.out.println(may5.withYear(2000));
//        System.out.println(may5.withMonth(3));
//        System.out.println(may5.withDayOfMonth(4));
//        System.out.println(may5.withDayOfYear(126));
//        System.out.println(may5.with(ChronoField.DAY_OF_YEAR, 130));
//        System.out.println(may5.plusYears(1));
//        System.out.println(may5.plusMonths(12));
//        System.out.println(may5.plusWeeks(52));
//        System.out.println(may5.plusDays(365));
//        System.out.println(may5.plus(365, ChronoUnit.DAYS));
//
//        LocalDate today = LocalDate.now();
//        System.out.println(today.isBefore(may5));
//        System.out.println(today.compareTo(may5));
//        System.out.println(today.isLeapYear());
//        System.out.println(may5.minusYears(2).isLeapYear());

//        System.out.println("-------------------------");

//        may5.datesUntil(may5.plusDays(7)).forEach(System.out::println);
//        may5.datesUntil(may5.plusYears(1), Period.ofDays(7)).forEach(System.out::println);

//        System.out.println("-------------------------");

        System.out.println(LocalTime.now());
        System.out.println(LocalTime.of(7, 0));
        System.out.println(LocalTime.of(7, 30, 15, 3243242));
        LocalTime seven30PM = LocalTime.parse("19:30:14.10000");
        System.out.println(seven30PM);
        System.out.println(seven30PM.get(ChronoField.AMPM_OF_DAY));
        System.out.println(seven30PM.get(ChronoField.HOUR_OF_DAY));
        System.out.println(seven30PM.get(ChronoField.HOUR_OF_AMPM));
        System.out.println(seven30PM.getHour());
        System.out.println(seven30PM.get(ChronoField.CLOCK_HOUR_OF_DAY));
        System.out.println(seven30PM.get(ChronoField.MINUTE_OF_HOUR));
        System.out.println(seven30PM.plus(24, ChronoUnit.HOURS));
        System.out.println(seven30PM.plusHours(24));
        System.out.println(seven30PM.range(ChronoField.HOUR_OF_DAY));
        System.out.println(seven30PM.range(ChronoField.MINUTE_OF_HOUR));
        System.out.println(seven30PM.range(ChronoField.MINUTE_OF_DAY));
        System.out.println(seven30PM.range(ChronoField.SECOND_OF_MINUTE));
        System.out.println(seven30PM.range(ChronoField.SECOND_OF_DAY));

        System.out.println("------------------------");
        LocalDateTime todayAndNow = LocalDateTime.now().withSecond(2);
        System.out.println(todayAndNow);

        LocalDateTime may5Noon = LocalDateTime.of(2022, 5, 5, 12, 0);
        System.out.println(may5Noon);
        System.out.printf("%tD %tr %n", may5Noon, may5Noon);
        System.out.printf("%1$tF %1$tT %n", may5Noon);

        System.out.println(todayAndNow.format(DateTimeFormatter.ISO_WEEK_DATE));
        System.out.println(todayAndNow.format(DateTimeFormatter.ISO_DATE));
        System.out.println(todayAndNow.format(DateTimeFormatter.BASIC_ISO_DATE));
        System.out.println(todayAndNow.format(DateTimeFormatter.ISO_DATE_TIME));
        System.out.println(todayAndNow.format(DateTimeFormatter.ISO_LOCAL_DATE));

        DateTimeFormatter dtf = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
        DateTimeFormatter dtf2 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        System.out.println(todayAndNow.format(dtf));
        System.out.println(todayAndNow.format(dtf2));

        System.out.println(ZoneId.systemDefault());
        System.out.println("Number of TZs=" + ZoneId.getAvailableZoneIds().size());

//        ZoneId.getAvailableZoneIds().stream()
//                .sorted()
//                .filter(s -> s.startsWith("America"))
//                .map(ZoneId::of)
//                .forEach(z -> System.out.println(z.getId() + ": " + z.getRules()));

        LocalDateTime dt = LocalDateTime.now();
        System.out.println(dt);

        System.setProperty("user.timezone", "America/Los_Angeles");
        System.out.println(ZoneId.systemDefault()); // Will print Europa/Istanbul (already cached for property is set)

        Instant instantNow = Instant.now();
        System.out.println(instantNow);
        DateTimeFormatter dtfZone = DateTimeFormatter.ofPattern("z:zzzz");
        for (ZoneId zoneId : ZoneId.getAvailableZoneIds().stream().filter(z -> z.startsWith("Europe/G")).map(ZoneId::of).toList()) {
            System.out.println(zoneId);
            System.out.println(instantNow.atZone(zoneId));
            System.out.println("\t" + instantNow.atZone(zoneId).format(dtfZone));
            System.out.println("\t" + zoneId.getRules().getDaylightSavings(instantNow));
            System.out.println("\t" + zoneId.getRules().isDaylightSavings(instantNow));
        }

        Instant dobInstance = Instant.parse("2020-07-02T18:54:22.34Z");
        ZoneId istanbulZone = ZoneId.of("Europe/Istanbul");
        System.out.println(dobInstance.atZone(istanbulZone) + "\n" +
                "\t" + istanbulZone.getDisplayName(TextStyle.FULL, Locale.of("tr", "TR")) +
                " (" + instantNow.atZone(istanbulZone).format(dtfZone) + ")\n" +
                "\t" + istanbulZone.getRules().getDaylightSavings(instantNow) + " : " + istanbulZone.getRules().getOffset(instantNow));
        ZonedDateTime dob = ZonedDateTime.ofInstant(dobInstance, istanbulZone);
        System.out.println(dob.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL)));
        LocalDateTime dobSydney = LocalDateTime.ofInstant(dobInstance, ZoneId.of("Australia/Sydney"));
        System.out.println(dobSydney.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));
        ZonedDateTime dobNewYork = dob.withZoneSameInstant(ZoneId.of("America/New_York"));
        System.out.println(dobNewYork);
        System.out.println("Time passed after Epoch time: " + Period.between(LocalDate.EPOCH, dob.toLocalDate()));
        System.out.println("Time passed after Epoch time: " + Duration.between(Instant.EPOCH, dob.toInstant().atOffset(ZoneOffset.UTC)));
        LocalDateTime dob2 = dobSydney.plusYears(2).plusMonths(4).plusDays(4).plusHours(3).plusMinutes(22).plusSeconds(2);
        System.out.println(dob2);

        for (ChronoUnit u : ChronoUnit.values()) {
            if (u.isSupportedBy(LocalDate.EPOCH)) {
                long val = u.between(LocalDate.EPOCH, dob2);
                System.out.println(u + " past= " + val);
            } else {
                System.out.println("-- Not supported: " + u);
            }
        }

        System.out.printf("First day of next month: %1$tD %1$tT %n", ZonedDateTime.now().with(TemporalAdjusters.firstDayOfNextMonth()));
        System.out.printf("First day of next month: %1$tF %1$tr %n", ZonedDateTime.now().with(TemporalAdjusters.firstDayOfNextMonth()));
    }
}
