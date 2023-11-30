package co.ayo.jmc.collections.treeset;

import java.util.Iterator;
import java.util.NavigableSet;
import java.util.Objects;
import java.util.TreeSet;

public class Theatre {

    private final String name;
    private final int seatsInRow;
    private NavigableSet<Seat> seats;

    public Theatre(String name, int rows, int totalSeats) {
        assert rows <= 26;
        this.name = name;
        this.seatsInRow = totalSeats / rows;
        createSeats(rows);
    }

    private void createSeats(int rows) {
        seats = new TreeSet<>();
        for (int row = 0; row < rows; row++) {
            for (int seatNumber = 1; seatNumber <= this.seatsInRow; seatNumber++) {
                seats.add(new Seat((char) ('A' + row), seatNumber));
            }
        }
    }

    public void reserveSeat(char row, int seat) {
        Seat reserve = new Seat(row, seat);
        reserve = seats.ceiling(reserve);
        if (reserve != null) {
            if (reserve.isReserved()) {
                System.out.println("Already reserved");
            } else {
                reserve.setReserved(true);
            }
        } else {
            System.out.println("Seat not found");
        }
    }

    public void reserveSeats(int reservations, char firstRow, char lastRow, int firstSeat, int lastSeat) {
        if (reserveSeatsValidationFailed(reservations, firstRow, lastRow, firstSeat, lastSeat)) return;

        int countReserved = 0;
        for (char row = firstRow; row <= lastRow && countReserved < reservations; row++) {
            NavigableSet<Seat> seatsToReserveInRow = seats.subSet(new Seat(row, firstSeat), true, new Seat(row, lastSeat), true);
            Iterator<Seat> seatIterator = seatsToReserveInRow.iterator();
            while (countReserved < reservations && seatIterator.hasNext()) {
                Seat seatToReserve = seatIterator.next();
                if (!seatToReserve.isReserved()) {
                    seatToReserve.setReserved(true);
                    countReserved++;
                } else {
                    System.out.println("This seat is already reserved: " + seatToReserve);
                }
            }
        }
        if (countReserved < reservations) {
            System.out.printf("%d seats could not be reserved.\n", reservations - countReserved);
        }
    }

    private boolean reserveSeatsValidationFailed(int reservations, char firstRow, char lastRow, int firstSeat, int lastSeat) {
        if (firstRow < 'A') {
            System.out.println("Wrong first row sent!");
            return true;
        }
        if (lastRow >= 'A' + seats.size() / seatsInRow) {
            System.out.println("Wrong last row sent!");
            return true;
        }
        if (firstSeat < 1) {
            System.out.println("Wrong first seat sent!");
            return true;
        }
        if (firstSeat > lastSeat) {
            System.out.println("Wrong first and seat sent!");
            return true;
        }
        if (lastSeat > seatsInRow) {
            System.out.println("Wrong last seat sent!");
            return true;
        }
        if ((lastSeat - firstSeat + 1) * (lastRow - firstRow + 1) < reservations) {
            System.out.printf("""
            Wrong parameters. Not enough seats selected for that much of reservations: %1$c[%3$03d-%4$03d]-%2$c[%3$03d-%4$03d]
            """, firstRow, lastRow, firstSeat, lastSeat);
            return true;
        }
        return false;
    }

    public void printSeatMap() {
        System.out.printf("Seats in %s:\n", name);
        System.out.println("-".repeat(30));

        Seat seat = seats.first();
        while (seat != null && seats.higher(seat) != null) {
            for (int i = 0; i < this.seatsInRow && seat != null; i++) {
                System.out.printf("%s%-5s", seat, seat.reserved ? "(R)" : "");
                seat = seats.higher(seat);
            }
            System.out.println();
        }
    }

    private class Seat implements Comparable<Seat> {
        private final char row;
        private final int seatNumber;
        private boolean reserved;

        Seat(char row, int seatNumber) {
            this.row = row;
            this.seatNumber = seatNumber;
            this.reserved = false;
        }

        @Override
        public String toString() {
            return "%c%03d".formatted(row, seatNumber);
        }

        void setReserved(boolean reserved) {
            this.reserved = reserved;
        }

        boolean isReserved() {
            return reserved;
        }

        @Override
        public int compareTo(Seat seat) {
            if (seat.row == this.row) {
                return this.seatNumber - seat.seatNumber;
            }
            return this.row - seat.row;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Seat seat = (Seat) o;
            return row == seat.row && seatNumber == seat.seatNumber;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, seatNumber);
        }
    }
}
