package com.ahmet.completejavadevelopercourse.collections.treeset;

public class Main {

    public static void main(String[] args) {
        Theatre theatre = new Theatre("Mersin Şehir Tiyayotrosu", 10, 50);
//        theatre.printSeatMap();

//        theatre.reserveSeat('F', 4);
//        theatre.printSeatMap();

        theatre.reserveSeats(3, 'A', 'B', 2, 3);

        theatre.reserveSeats(13, 'A', 'C', 2, 3);

        theatre.printSeatMap();
    }
}
