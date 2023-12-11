package co.ayo;

import java.util.Scanner;

public class Ceviri {
    public static void main(String[] args) {
        String orijinal = new Scanner(System.in).nextLine();
        if (orijinal.length() > HarfSayisi.MAX_HARF_SAYISI) {
            System.out.println("Çok uzun metin");
        } else {
            System.out.println("Çeviri: " + cevir(orijinal));
        }
    }

    private static String cevir(String metin) {
        return "Türkçe";
    }
}
