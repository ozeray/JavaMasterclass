package com.ahmet;

public class TowerBreakers {

    public static void main(String[] args) {
        System.out.println(towerBreakers(2 , 2));
    }

    private static int towerBreakers(int n, int m) {
        boolean gameFinished = false;
        int winner = -1;

        while (!gameFinished) {
            for (int i = 1; i <= 2; i++) {

                // if finished:
                gameFinished = true;
                winner = i;
            }
        }

        return winner;
    }

}
