package co.ayo;

import java.util.Scanner;

public class HarfSayisi {
    public static final int MAX_HARF_SAYISI = 50;
    public static void main(String[] args) {
        System.out.println("Cümleyi giriniz: ");
        Scanner scanner = new Scanner(System.in);
//        while (true) {
            String cumle = scanner.nextLine().replace(" ", "");
        System.out.println(cumle);
//            if (cumle.equals("-")) {
//                break;
//            }
            int harfSayisi = cumle.length() > MAX_HARF_SAYISI ? MAX_HARF_SAYISI : cumle.length();
            System.out.println("Cümledeki harf sayisi: " + harfSayisi);
//        }
    }
}
