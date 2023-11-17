package com.ahmet.completejavadeveloper.collections.enumset;

import java.util.*;

public class Main {

    enum WeekDay {SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY}

    public static void main(String[] args) {
        var workDays = List.of(WeekDay.MONDAY, WeekDay.TUESDAY, WeekDay.WEDNESDAY, WeekDay.THURSDAY, WeekDay.FRIDAY);
        var workDaysSet = EnumSet.copyOf(workDays);

        System.out.println("-------------------");
        System.out.println(workDaysSet.getClass().getSimpleName());
        System.out.println(workDaysSet);

        System.out.println("-------------------");
        var allDays = EnumSet.allOf(WeekDay.class);
        System.out.println(allDays);

        System.out.println("-------------------");
        var weekendSet = EnumSet.complementOf(workDaysSet);
        System.out.println(weekendSet);

        System.out.println("-------------------");
        var midWeekSet = EnumSet.range(WeekDay.TUESDAY, WeekDay.THURSDAY);
        System.out.println(midWeekSet);

        System.out.println("-------------------");
        var employeeMap = new EnumMap<WeekDay, String[]>(WeekDay.class);
        employeeMap.put(WeekDay.TUESDAY, new String[]{"Ali", "Veli"});
        employeeMap.put(WeekDay.THURSDAY, new String[]{"Zeki", "Metin"});
        employeeMap.forEach((weekDay, employees) -> System.out.println(weekDay + ": " + Arrays.toString(employees)));
    }
}
