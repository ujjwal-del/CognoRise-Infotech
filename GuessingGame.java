import java.util.Random;
import java.util.Scanner;

class GuessingGame {
    public static void main(String[] args) {
        Random random = new Random();
        try (Scanner scanner = new Scanner(System.in)) {
            int randomNumber = random.nextInt(100) + 1;
            int maxAttempts = 5;  
            int attempts = 0;
            boolean hasGuessedCorrectly = false;
            System.out.println("Welcome to the NumberGuessing Game!");
            System.out.println("Try to guess the number");
            while (attempts < maxAttempts && !hasGuessedCorrectly) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;
                if (userGuess == randomNumber) {
                    System.out.println("Congratulations! You've guessed the number correctly.");
                    hasGuessedCorrectly = true;
                } else if (userGuess < randomNumber) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Too high! Try again.");
                }
            }
            if (!hasGuessedCorrectly) {
                System.out.println("Sorry! You've used all attempts. The correct number was: " + randomNumber);
            }
        }
    }
}