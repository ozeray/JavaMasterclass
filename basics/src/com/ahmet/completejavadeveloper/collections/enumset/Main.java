package com.ahmet.completejavadeveloper.collections.enumset;

import java.util.EnumSet;
import java.util.List;

public class Main {

    enum WeekDay {SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY}

    public static void main(String[] args) {
        var workDays = List.of(WeekDay.MONDAY, WeekDay.TUESDAY, WeekDay.WEDNESDAY, WeekDay.THURSDAY, WeekDay.FRIDAY);
        var workDaysSet = EnumSet.copyOf(workDays);

        System.out.println(workDaysSet.getClass().getSimpleName());
        System.out.println(workDaysSet);
    }
}
