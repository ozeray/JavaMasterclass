package com.ahmet.jmc.locales;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {
        Locale locale = Locale.getDefault();
        System.out.println(locale);
//        Locale.setDefault(Locale.forLanguageTag("tr"));
//        System.out.println(Locale.getDefault());

        Locale en = Locale.forLanguageTag("en");
        Locale enAU = Locale.of("en", "AU");
        Locale enIN = new Locale.Builder().setLanguage("en").setRegion("IN").build();
        Locale enNZ = new Locale.Builder().setLanguage("en").setRegion("NZ").build();

        DateTimeFormatter dtf = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        for (Locale l : List.of(Locale.US, Locale.UK, en, enAU, enIN, enNZ)) {
            System.out.println(l.getDisplayName() + " = " + LocalDateTime.now().format(dtf.withLocale(l)));
        }

        DateTimeFormatter wdayMonth = DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy");
        LocalDate may5 = LocalDate.of(2020, 5, 5);
        System.out.println("----------------------------");
        for (Locale l : List.of(Locale.CANADA, Locale.CANADA_FRENCH, Locale.FRANCE, Locale.JAPAN, Locale.GERMANY,
                Locale.TAIWAN, Locale.ITALY, new Locale.Builder().setLanguage("tr").setRegion("TR").build())) {
            System.out.println(l.getDisplayName() + " : " + l.getDisplayName(l) + " =\n\t" + may5.format(wdayMonth.withLocale(l)));
            // Alternative ways:
//            System.out.printf(l, "\t%1$tA, %1$tB %1$te, %1$tY %n", may5);
//            String strFormatted = String.format(l, "\t%1$tA, %1$tB %1$te, %1$tY %n", may5);
//            System.out.print(strFormatted);

            NumberFormat decimalInfo = NumberFormat.getNumberInstance(l);
            decimalInfo.setMaximumFractionDigits(6);
            System.out.println(decimalInfo.format(123456789.123456));

            NumberFormat currencyInfo = NumberFormat.getCurrencyInstance(l);
            Currency localCurrency = Currency.getInstance(l);
            System.out.println(currencyInfo.format(555.555) + " ["
                    + localCurrency.getCurrencyCode() + "] " +
                    localCurrency.getDisplayName() + "/" + localCurrency.getDisplayName(l));
        }

//        BigDecimal myLoan = new BigDecimal("1,000.50"); // Will throw exception. Comma cannot be usd inside BigDecimal constructor argument
        /*
            But if we take the same number via Scanner, no error!
            Scanner scanner = new Scanner(System.in);
    //        scanner.useLocale(...) (Can be used to differentiate between group separator and decimal point..)
            BigDecimal myLoan = scanner.nextBigDecimal();
         */

    }
}
