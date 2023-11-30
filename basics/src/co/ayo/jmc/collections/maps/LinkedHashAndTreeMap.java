package co.ayo.jmc.collections.maps;

import java.time.LocalDate;
import java.util.*;

public class LinkedHashAndTreeMap {

    private static final Map<String, Purchase> purchases = new LinkedHashMap<>();
    private static final NavigableMap<String, Student> students = new TreeMap<>();

    public static void main(String[] args) {
        Course jmc = new Course("jmc101", "Java Master Class", "Java");
        Course python = new Course("pyt101", "Python Master Class", "Python");

        addPurchase("Mary Martin", jmc, 129.99);
        addPurchase("Andy Martin", jmc, 139.99);
        addPurchase("Mary Martin", python, 149.99);
        addPurchase("Joe Jones", jmc, 149.99);
        addPurchase("Bill Brown", python, 119.99);

        addPurchase("Chuck Cheese", python, 119.99);
        addPurchase("Davey Jones", jmc, 139.99);
        addPurchase("Eva East", python, 139.99);
        addPurchase("Fred Forker", jmc, 139.99);
        addPurchase("Greg Brady", python, 129.99);

        System.out.println("------------------------------");
        purchases.forEach((key, purchase) -> System.out.println(key + ": " + purchase));
        System.out.println("------------------------------");
        students.forEach((key, student) -> System.out.println(key + ": " + student));

        NavigableMap<LocalDate, List<Purchase>> datedPurchases = preparePrintAndReturnDatedPurchases();
        printPurchasesAndStatisticsInFirstTwoWeeks(datedPurchases);

        printLastDayData(datedPurchases);

        printPurchasesCountPerDayInDescendingOrderOfDate(datedPurchases);

        System.out.println("-------------------------------");
        System.out.println("Poll examples");
        students.pollFirstEntry();
        students.pollLastEntry();
        students.forEach((key, student) -> System.out.println(key + ": " + student));


    }

    private static void printLastDayData(NavigableMap<LocalDate, List<Purchase>> datedPurchases) {
        LocalDate lastDate = datedPurchases.lastKey();
        var lastEntry = datedPurchases.lastEntry();
        List<Purchase> lastDayData = lastEntry.getValue();
        System.out.println("-------------------------------");
        System.out.println(lastDate + " purchases: " + lastDayData);
    }

    private static void printPurchasesCountPerDayInDescendingOrderOfDate(NavigableMap<LocalDate, List<Purchase>> datedPurchases) {
        System.out.println("-------------------------------");
        var reversed = datedPurchases.descendingMap();
        LocalDate purchaseDate = reversed.firstKey();
        var purchaseEntry = reversed.firstEntry();
        while (purchaseEntry != null) {
            List<Purchase> dayData = purchaseEntry.getValue();
            System.out.println(purchaseDate + " purchases: " + dayData.size());

            LocalDate nextDate = reversed.higherKey(purchaseDate);
            purchaseEntry = reversed.higherEntry(purchaseDate);
            purchaseDate = nextDate;
        }
    }

    private static NavigableMap<LocalDate, List<Purchase>> preparePrintAndReturnDatedPurchases() {
        NavigableMap<LocalDate, List<Purchase>> datedPurchases = new TreeMap<>();
        purchases.values().forEach(purchase ->
                datedPurchases.compute(purchase.purchaseDate(), (pDate, pList) -> {
                    List<Purchase> list = (pList == null) ? new ArrayList<>() : pList;
                    list.add(purchase);
                    return list;
                })
        );

        System.out.println("------------------------------");
        datedPurchases.forEach((key, purchases) -> System.out.println(key + ": " + purchases));

        return datedPurchases;
    }

    private static void printPurchasesAndStatisticsInFirstTwoWeeks(NavigableMap<LocalDate, List<Purchase>> datedPurchases) {
        int currentYear = LocalDate.now().getYear();
        LocalDate firstDate = LocalDate.ofYearDay(currentYear, 1);
        LocalDate firstWeek = firstDate.plusDays(7);
        Map<LocalDate, List<Purchase>> firstWeekPurchases = datedPurchases.headMap(firstWeek);
        Map<LocalDate, List<Purchase>> secondWeekPurchases = datedPurchases.tailMap(firstWeek);

        printWeeklyPurchasesAndCourseCounts(1, firstWeekPurchases);
        printWeeklyPurchasesAndCourseCounts(2, secondWeekPurchases);
    }

    private static void printWeeklyPurchasesAndCourseCounts(int period, Map<LocalDate, List<Purchase>> weeklyPurchases) {
        Map<String, Integer> weeklyCourseCounts = new TreeMap<>();
        System.out.println("------------------------------");
        weeklyPurchases.forEach((key, purchases) -> {
            System.out.println(key + ": " + purchases);
            purchases.forEach(purchase -> weeklyCourseCounts.merge(purchase.courseId(), 1, Integer::sum));
        });
        System.out.printf("Week %d Purchases: %s%n", period, weeklyCourseCounts);
    }

    private static void addPurchase(String name, Course course, double price) {
        Student existingStudent = students.get(name);
        if (existingStudent == null) {
            existingStudent = new Student(name, course);
            students.put(name, existingStudent);
        } else {
            existingStudent.addCourse(course);
        }

        int day = new Random().nextInt(1, 15);
        String purchaseKey = course.courseId() + "_" + existingStudent.getId();
        int year = LocalDate.now().getYear();
        Purchase purchase = new Purchase(course.courseId(), existingStudent.getId(), price, year, day);
        purchases.put(purchaseKey, purchase);
    }
}
