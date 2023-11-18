package com.ahmet.completejavadeveloper.datetime;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

public class Main {

    public static void main(String[] args) {
        System.out.println(LocalDate.now());

        System.out.println(LocalDate.of(2022, 5, 5));
        System.out.println(LocalDate.of(2022, Month.MAY, 5));
        System.out.println(LocalDate.ofYearDay(2022, 125));
        System.out.println(LocalDate.parse("2022-05-05"));

        LocalDate may5 = LocalDate.parse("2022-05-05", DateTimeFormatter.ISO_DATE);
        System.out.println(may5);
        System.out.println(may5.getYear());
        System.out.println(may5.getMonth());
        System.out.println(may5.getMonthValue());
        System.out.println(may5.getDayOfMonth());
        System.out.println(may5.getDayOfWeek());
        System.out.println(may5.getDayOfYear());
        System.out.println(may5.get(ChronoField.YEAR));
        System.out.println(may5.get(ChronoField.MONTH_OF_YEAR));
        System.out.println(may5.get(ChronoField.DAY_OF_MONTH));
        System.out.println(may5.get(ChronoField.DAY_OF_YEAR));

        System.out.println(may5.withYear(2000));
        System.out.println(may5.withMonth(3));
        System.out.println(may5.withDayOfMonth(4));
        System.out.println(may5.withDayOfYear(126));
        System.out.println(may5.with(ChronoField.DAY_OF_YEAR, 130));
        System.out.println(may5.plusYears(1));
        System.out.println(may5.plusMonths(12));
        System.out.println(may5.plusWeeks(52));
        System.out.println(may5.plusDays(365));
        System.out.println(may5.plus(365, ChronoUnit.DAYS));

        LocalDate today = LocalDate.now();
        System.out.println(today.isBefore(may5));
        System.out.println(today.compareTo(may5));
        System.out.println(today.isLeapYear());
        System.out.println(may5.minusYears(2).isLeapYear());
    }
}
