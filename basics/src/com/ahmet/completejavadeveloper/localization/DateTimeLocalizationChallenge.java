package com.ahmet.completejavadeveloper.localization;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.zone.ZoneRules;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DateTimeLocalizationChallenge {

    private record Employee(String name, Locale locale, ZoneId zone) {

        public Employee(String name, Locale locale, String zone) {
            this(name, locale, ZoneId.of(zone));
        }

        public Employee(String name, String locale, String zone) {
            this(name, Locale.forLanguageTag(locale), ZoneId.of(zone));
        }

        String getDateInfo(ZonedDateTime zdt, DateTimeFormatter dtf) {
            return "%s [%s] : %s".formatted(name, zone, zdt.format(dtf.localizedBy(locale)));
        }
    }

    public static void main(String[] args) {
        Employee jane = new Employee("Jane", Locale.US, "America/New_York");
        Employee ahmet = new Employee("Ahmet", "tr-TR", "Europe/Istanbul");

        ZoneRules janeRules = jane.zone.getRules();
        ZoneRules ahmetRules = ahmet.zone.getRules();
        System.out.println(jane + " " + janeRules);
        System.out.println(ahmet + " " + ahmetRules);

        ZonedDateTime janeNow = ZonedDateTime.now(jane.zone);
        ZonedDateTime ahmetNow = ZonedDateTime.of(janeNow.toLocalDateTime(), ahmet.zone);
        long hoursBetween = Duration.between(ahmetNow, janeNow).toHours();
        long minutesBetween = Duration.between(ahmetNow, janeNow).toMinutesPart();
        System.out.println("Ahmet is " + Math.abs(hoursBetween) + " hours " + Math.abs(minutesBetween) + " minutes " +
                ((hoursBetween < 0) ? "behind" : "ahead"));

        System.out.println("Jane in daylight savings? " +
                janeRules.isDaylightSavings(janeNow.toInstant()) + " " +
                janeRules.getDaylightSavings(janeNow.toInstant()) + ": " +
                janeNow.format(DateTimeFormatter.ofPattern("zzzz z")));

        System.out.println("Ahmet in daylight savings? " +
                ahmetRules.isDaylightSavings(ahmetNow.toInstant()) + " " +
                ahmetRules.getDaylightSavings(ahmetNow.toInstant()) + ": " +
                ahmetNow.format(DateTimeFormatter.ofPattern("zzzz z")));

        var map = schedule(jane, ahmet, 10);
        DateTimeFormatter dtf = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL, FormatStyle.SHORT);
        for (LocalDate ldt : map.keySet()) {
            System.out.println(ldt.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)));
            for (ZonedDateTime zdt : map.get(ldt)) {
                System.out.println("\t" + ahmet.getDateInfo(zdt, dtf) + " <---> " +
                        jane.getDateInfo(zdt.withZoneSameInstant(jane.zone()), dtf));
            }
        }

    }

    private static Map<LocalDate, List<ZonedDateTime>> schedule(Employee first, Employee second, int days) {
        Predicate<ZonedDateTime> rules = zdt -> zdt.getDayOfWeek() != DayOfWeek.SATURDAY &&
                                                zdt.getDayOfWeek() != DayOfWeek.SUNDAY &&
                                                zdt.getHour() >= 7 && zdt.getHour() < 21;

        LocalDate startingDate = LocalDate.now().plusDays(1);

        return startingDate.datesUntil(startingDate.plusDays(days + 1))
                .map(dt -> dt.atStartOfDay(first.zone()))
                .flatMap(zdt -> IntStream.range(0, 24).mapToObj(zdt::withHour))
                .filter(rules) // Hours suitable for the first employee
                .map(dtz -> dtz.withZoneSameInstant(second.zone()))
                .filter(rules) // Hours suitable for the second employee (so, interception ensured)
                .collect(Collectors.groupingBy(ZonedDateTime::toLocalDate, TreeMap::new, Collectors.toList()));
    }
}
