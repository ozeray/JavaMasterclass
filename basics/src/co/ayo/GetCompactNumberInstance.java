package co.ayo;

import java.text.NumberFormat;
import java.util.Locale;

public class GetCompactNumberInstance {

    public static void main(String[] args) {
        NumberFormat turkishFormat = NumberFormat.getCompactNumberInstance(
                Locale.of("tr", "TR"), NumberFormat.Style.SHORT);
        turkishFormat.setMaximumFractionDigits(2);
        System.out.println(turkishFormat.format(2459));

        NumberFormat englishFormat = NumberFormat.getCompactNumberInstance(
                Locale.of("en", "US"), NumberFormat.Style.SHORT);
        englishFormat.setMaximumFractionDigits(2);
        System.out.println(englishFormat.format(2459));

        NumberFormat turkishFormatLong = NumberFormat.getCompactNumberInstance(
                Locale.of("tr", "TR"), NumberFormat.Style.LONG);
        turkishFormatLong.setMaximumFractionDigits(2);
        System.out.println(turkishFormatLong.format(2459));

        NumberFormat englishFormatLong = NumberFormat.getCompactNumberInstance(
                Locale.of("en", "US"), NumberFormat.Style.LONG);
        englishFormatLong.setMaximumFractionDigits(2);
        System.out.println(englishFormatLong.format(2459));
    }
}
