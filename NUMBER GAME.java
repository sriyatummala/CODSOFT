import java.util.Random;
import java.util.Scanner;

public class numberGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        int LB = 1;
        int UB = 100;
        int attemptsLimit = 4;
        int rounds = 0;
        int totalAttempts = 0;

        System.out.println("Welcome to the Number Guessing Game!");
        System.out.println("Guess the number between " + LB + " and " + UB);

        boolean playAgain = true;
        while (playAgain) {
            int targetNumber = random.nextInt(UB - LB + 1) + LB;
            int atmp = 0;
            boolean correctGuess = false;

            while (atmp < attemptsLimit && !correctGuess) {
                System.out.print("Enter your guess:");
                try {
                    int userGuess = scanner.nextInt();
                    atmp++;

                    if (userGuess == targetNumber) {
                        System.out.println("Congratulations! You guessed the correct number in " + atmp + " attempts.");
                        correctGuess = true;
                    } else if (userGuess < targetNumber) {
                        System.out.println("Too low. Try again.");
                    } else {
                        System.out.println("Too high. Try again.");
                    }
                } catch (java.util.InputMismatchException e) {
                    System.out.println("Invalid input. Please enter an integer.");
                    scanner.next(); // consume the invalid input
                }
            }

            totalAttempts += atmp;
            rounds++;

            System.out.println("The correct number was: " + targetNumber);

            System.out.print("Do you want to play again? (yes/no): ");
            String playAgainInput = scanner.next().toLowerCase();
            playAgain = playAgainInput.equals("yes");
        }

        System.out.println("Thanks for playing!");
        System.out.println("Total Rounds: " + rounds);
        System.out.println("Total Attempts: " + totalAttempts);
    }
}
