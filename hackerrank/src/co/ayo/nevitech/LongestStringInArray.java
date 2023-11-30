package co.ayo.nevitech;

import java.util.Comparator;
import java.util.stream.Stream;

public class LongestStringInArray {

    public static void main(String[] args) {
        String[] animalNames = {"cat", "rabbit", "horse", "goat", "rooster", "ooooooooooooooo"};

        String longest = animalNames[0];
        for (int i = 1; i < animalNames.length; i++) {
            if (animalNames[i].length() > longest.length()) {
                longest = animalNames[i];
            }
        }
        System.out.println(longest);

        longest = Stream.of(animalNames).max(Comparator.comparingInt(String::length)).get();
        System.out.println(longest);

        long longestLength = Stream.of(animalNames).mapToInt(String::length).max().getAsInt();
        System.out.println(longestLength);
    }
}
