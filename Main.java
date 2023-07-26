import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        startGame();
    }

    public static void startGame() {
        Scanner inputScanner = new Scanner(System.in);

        System.out.println("Welcome to Mastermind!");

        // Set up the game by asking for user preferences
        System.out.print("Enter the number of colors (6-10): ");
        int numColors = inputScanner.nextInt();

        System.out.print("Enter the code length (3 or 4): ");
        int codeLength = inputScanner.nextInt();

        System.out.print("Enter the maximum number of guesses: ");
        int maxGuesses = inputScanner.nextInt();

        MastermindGame game = new MastermindGame(numColors, codeLength, maxGuesses);

        // Start the game loop
        while (!game.isGameOver()) {
            System.out.println("Remaining Guesses: " + game.getRemainingGuesses());
            System.out.print("Enter your guess from Red, Green, Blue, Yellow, Orange, Pink, Purple, Cyan, White, Black (e.g., RGBY): ");
            String guess = inputScanner.next().toUpperCase();

            String result = game.evaluateGuess(guess);
            System.out.println(result);
        }

        // Display the final result and the secret code when the game ends
        if (!game.isCodeGuessed()) {
            System.out.println("Game Over! The secret code was " + game.getSecretCode() + ".");
        }

        // Ask the player if they want to play again
        System.out.print("Play again? (Y/N): ");
        String playAgain = inputScanner.next();
        if (playAgain.equalsIgnoreCase("Y")) {
            startGame();
        } else {
            System.out.println("Thank you for playing Mastermind!");
        }

        inputScanner.close();
    }
}
