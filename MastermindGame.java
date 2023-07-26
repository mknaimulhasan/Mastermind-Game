import java.util.*;

public class MastermindGame {
    private final List<String> colors;
    private final int codeLength;
    private final int maxGuesses;
    private String secretCode;
    private int remainingGuesses;
    private boolean gameOver;

    public MastermindGame(int numColors, int codeLength, int maxGuesses) {
        if (numColors < 6 || numColors > 10) {
            throw new IllegalArgumentException("Number of colors must be between 6 and 10.");
        }

        if (codeLength < 3 || codeLength > 4) {
            throw new IllegalArgumentException("Code length must be 3 or 4.");
        }

        this.colors = Arrays.asList("Red", "Green", "Blue", "Yellow", "Orange", "Pink", "Purple", "Cyan", "White", "Black")
                .subList(0, numColors);
        this.codeLength = codeLength;
        this.maxGuesses = maxGuesses;

        remainingGuesses = maxGuesses;
        gameOver = false;

        generateSecretCode();
    }

    private void generateSecretCode() {
        List<String> tempColors = new ArrayList<>(colors);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < codeLength; i++) {
            int randomIndex = new Random().nextInt(tempColors.size());
            sb.append(tempColors.get(randomIndex)).append(" ");
            tempColors.remove(randomIndex);
        }

        this.secretCode = sb.toString().trim();
    }

    public String evaluateGuess(String guess) {
        if (guess.length() != codeLength) {
            return "Invalid guess. The length of the guess should be " + codeLength + ".";
        }
    
        List<String> tempColors = new ArrayList<>(colors);
        int exactMatches = 0;
        int colorMatches = 0;
    
        for (int i = 0; i < codeLength; i++) {
            String guessColor = guess.substring(i, i + 1);
            String secretColor = secretCode.substring(i, i + 1);
    
            if (guessColor.equals(secretColor)) {
                exactMatches++;
            } else {
                if (tempColors.contains(guessColor)) {
                    colorMatches++;
                    tempColors.remove(guessColor);
                }
            }
        }
    
        remainingGuesses--;
    
        if (exactMatches == codeLength) {
            gameOver = true;
            return "Congratulations! You guessed the secret code!";
        } else {
            if (remainingGuesses == 0) {
                gameOver = true;
                return "Game Over! The secret code was: " + secretCode;
            } else {
                StringBuilder resultBuilder = new StringBuilder();
                resultBuilder.append("+".repeat(exactMatches));
                resultBuilder.append("-".repeat(colorMatches));
                return resultBuilder.toString();
            }
        }
    }
    
    
    


    public int getRemainingGuesses() {
        return remainingGuesses;
    }

    public String getSecretCode() {
        return secretCode;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isCodeGuessed() {
        return false;
    }
}
