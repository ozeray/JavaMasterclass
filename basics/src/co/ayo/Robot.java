package co.ayo;

import java.util.Scanner;

public class Robot {
    public static void main(String[] args) {
        System.out.println("Kaç adım atsın?");
        int adimSayisi = new Scanner(System.in).nextInt();

        Robot robot = new Robot();
////        for (int i = 0; i < adimSayisi; i++) {
////            robot.ilerle();
////        }
//        robot.ilerle(adimSayisi);

//        robot.birAdimIlerleSolaDon();
        robot.adimSayisiKadarIlerleSonraSolaDon(adimSayisi);
    }

    private void adimSayisiKadarIlerleSonraSolaDon(int adimSayisi) {
        ilerle(adimSayisi);
        solaDon();
    }

    private void birAdimIlerleSolaDon() {
        birAdimIlerle();
        solaDon();
    }

    private void solaDon() {
        System.out.print("|");
    }

    private void birAdimIlerle() {
        System.out.print("_");
    }

    private void ilerle(int adimSayisi) {
        for (int i = 0; i < adimSayisi; i++) {
            birAdimIlerle();
        }
    }
}
