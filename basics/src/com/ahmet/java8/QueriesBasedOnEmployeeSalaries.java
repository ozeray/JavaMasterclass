package com.ahmet.java8;

import java.util.*;
import java.util.stream.Collectors;

public class QueriesBasedOnEmployeeSalaries {

    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(new Employee("Ahmet", 3300d),
                new Employee("Veli", 2000d),
                new Employee("Ali", 4000d),
                new Employee("Burhan", 4000d),
                new Employee("Zeki", 2200d),
                new Employee("Kadir", 3400d),
                new Employee("Selim", 3400d));

        System.out.println("Employees in descending order based on salary:");
        employees.stream().sorted((o1, o2) -> (int)(o2.salary() - o1. salary()))
                .forEach(System.out::println);
        System.out.println();

        System.out.println("Top 3 employees based on salary:");
        employees.stream().sorted((o1, o2) -> (int)(o2.salary() - o1. salary())).limit(3)
                .forEach(System.out::println);
        System.out.println();

        System.out.println("Employees with lower salaries compared to highest 3rd salary:");
        employees.stream().sorted((o1, o2) -> (int)(o2.salary() - o1. salary())).skip(3)
                .forEach(System.out::println);
        System.out.println();

        System.out.println("Sorted employees map based on salary order:");
        employees.stream().collect(Collectors.groupingBy(Employee::salary, TreeMap::new, Collectors.toSet()))
                .forEach((sal, empSet) -> System.out.println(sal + " => " + empSet));
        System.out.println();

        System.out.println("Employee statistics:");
        IntSummaryStatistics summaryStatistics = employees.stream().map(Employee::salary)
                .mapToInt(Double::intValue).summaryStatistics();
        System.out.println(summaryStatistics);

        System.out.println("Employee statistics with DoubleSummaryStatistics:");
        DoubleSummaryStatistics doublesummaryStatistics = employees.stream()
                .collect(Collectors.summarizingDouble(Employee::salary));
        System.out.println(doublesummaryStatistics);

        System.out.println("Employee statistics with DoubleSummaryStatistics, alternative way:");
        DoubleSummaryStatistics doublesummaryStatisticsAlt = employees.stream().map(Employee::salary)
                .mapToDouble(s -> s).summaryStatistics();
        System.out.println(doublesummaryStatisticsAlt);
    }
}
