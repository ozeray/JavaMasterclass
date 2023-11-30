package co.ayo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class HiLoGame {
    private Random random;
    private int generated;
    private int numOfAttempts;
    private BufferedReader reader;

    public static void main(String[] args) throws IOException {
        new HiLoGame().start();
    }

    @SuppressWarnings({"AssignmentUsedAsCondition", "ConstantConditions"})
    private void start() throws IOException {
        random = new Random();
        reader = new BufferedReader(new InputStreamReader(System.in));

        boolean wantToPlay;
        boolean firstTime = true;

        do {
            System.out.println("Do you want to play HiLo game??");

            while (wantToPlay = prompt()) {
                generated = generate();
                numOfAttempts = 0;
                if (firstTime) {
                    describeRules();
                    firstTime = false;
                }
                playGame();
            }
        } while (wantToPlay);

        System.out.println("Thanks for playing...");
        reader.close();
    }

    private void playGame() {
        while (numOfAttempts < 6) {
            int guess = getNextGuess();
            if (guess > generated) {
                System.out.println("HI");
            } else if (guess < generated) {
                System.out.println("LO");
            } else {
                System.out.println("Brave Soul, You guessed the right number!! Congratulations !!");
                return;
            }
            numOfAttempts++;
        }

        System.out.println("Sorry, you didn't guess the right number in six attempts !!");
        System.out.println("The secret number was " + generated);
    }

    private int getNextGuess() {
        boolean inputOk = false;
        int number = 0;
        String input;
        while (!inputOk) {
            try {
                System.out.println("Guess: ");
                input = reader.readLine();
                number = Integer.parseInt(input);
                if (number <= 100 && number >= 1) {
                    inputOk = true;
                }
            } catch (Exception e) {
                System.out.println("Really? You didn't read the rules boy. Your number is not between 1 and 100 ("+ number + ").");
            }

        }

        return number;
    }

    private void describeRules() {
        System.out.println("\nOnly 2 Rules:");
        System.out.println("1) Guess the secret number in maximum 6 tries.");
        System.out.println("2) The secret number is an integer between 1 and 100, inclusive :-)\n\n");
    }

    private int generate() {
        return random.nextInt(100) + 1;
    }

    private boolean prompt() throws IOException {
        boolean answer = false;
        boolean inputOk = false;
        while (!inputOk) {
            System.out.println("Y / N : ");
            String input = reader.readLine();
            if ("y".equalsIgnoreCase(input)) {
                inputOk = true;
                answer = true;
            } else if ("n".equalsIgnoreCase(input)) {
                inputOk = true;
            } else {
                System.out.println("Try again: ");
            }
        }
        return answer;
    }
}
