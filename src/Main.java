import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

// TODO: add case sensitiveness_
// TODO: don't count same incorrect guess more than one
// TODO: draw the actual hangman
//
// TODO: restart game at the end - // DO - WHILE Continue? y / n
//
// TODO: add colors somehow (very optional)
// TODO: add information about how many guesses left (Enter a letter (...))
//
// TODO: show incorrect guesses (letters which user already tried) - // usedChar
//
public class Main {
    public static void main(String[] args) {
        String pokracovat;
        do {
            final String[] words = {"java", "hangman", "skillmea", "academy", "computer"};
            final Random random = new Random();
            final String wordToGuess = selectRandomWord(random, words);
            String hiddenWord = generateHiddenWord(wordToGuess);

            final int MAX_INCORRECT_GUESSES = 6;
            int incorrectGuessesCounter = 0;
            //final int allreadyUse = 0;
            List<String> usedChar = new ArrayList();
            System.out.println(usedChar);


            final Scanner scanner = new Scanner(System.in);


            System.out.println("Welcome to Hangman");
            System.out.println();
            System.out.println("Guess the word: " + hiddenWord);
            System.out.println();
            // continue cycle while there are guesses left and hiddenWord is still not solved
            while (incorrectGuessesCounter < MAX_INCORRECT_GUESSES && hiddenWord.contains("_")) {
                System.out.println("Used charracters :" + (usedChar));
                //System.out.println();
                System.out.println("You have " + (incorrectGuessesCounter) + " failed attemps. " + "Enter a letter ");
                final char guess = scanLetter(scanner);
                usedChar.add(String.valueOf(guess));
                //    System.out.println("Tvoje pokusy " + usedChar);


                if (hiddenWord.contains(String.valueOf(guess))) {
                    System.out.println("This letter is already revealed" + (usedChar));
                    System.out.println("You guessed it " + hiddenWord);
                } else if (wordToGuess.contains(String.valueOf(guess))) {
                    // revealnut letter / pismenko
                    hiddenWord = revealLetters(wordToGuess, hiddenWord, guess);
                    System.out.println("Correct guess! Updated word: " + hiddenWord);
                } else {
                    incorrectGuessesCounter++;

                    //tady ontrola jestli uz bylo pismenko pouzito

                    System.out.println("Incorrect guess, you have (" + (MAX_INCORRECT_GUESSES - incorrectGuessesCounter) + ") guesses left");
                    System.out.println("You guessed it " + hiddenWord);
                }
            }

            // tu mozem byt tak ze som to uhadol cele, alebo mi dosli guesses
            if (!hiddenWord.contains("_")) {
                System.out.println("Congratulations, you guessed it: " + wordToGuess);
            } else {
                System.out.println("Sorry, you have run out of guesses. It was " + wordToGuess);
            }
            System.out.println("Your attempts " + usedChar);
            System.out.println();
            System.out.println("Do you want to continue? y/n");     //dotaz jestli chces zacit znova
            System.out.println();
            pokracovat = scanner.nextLine();
        } while (pokracovat.equals("y"));
    }

//    public static char scanContinue(Scanner scanner) {
//
//        while (true) {
//            try {
//                final String line = scanner.nextLine();
//
//                if (line.length() != 1) {
//                    throw new Exception("Line length is not 1. Please enter a single letter");
//                }
//
//                if (!Character.isLetter(line.charAt(0))) {
//                    throw new Exception("Character is not a letter. Please enter a single letter");
//                }
//
//                return line.charAt(0);
//            } catch (Exception e) {
//                System.out.println("Invalid input: " + e.getMessage());
//            }
//        }
//    }
//

    public static String revealLetters(String word, String hiddenWord, char letter) {
        // ak napriklad pride pismenko 'a', word je 'java', a hidden word je '____'
        // tak funkcia vrati '_a_a' (j a v este stale nie su revealeed, ale 'a' sme odhalili)
        final char[] hiddenWordChars = hiddenWord.toCharArray();

        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) == letter) {
                hiddenWordChars[i] = letter;
            }
        }

        return String.valueOf(hiddenWordChars);
    }

    public static char scanLetter(Scanner scanner) {
        while (true) {
            try {
                final String line = scanner.nextLine();

                if (line.length() != 1) {
                    throw new Exception("Line length is not 1. Please enter a single letter");
                }

                if (!Character.isLetter(line.charAt(0))) {
                    throw new Exception("Character is not a letter. Please enter a single letter");
                }

                return line.charAt(0);
            } catch (Exception e) {
                System.out.println("Invalid input: " + e.getMessage());
            }
        }
    }

    /**
     * Generates hidden word. For example, from word 'java' will generate '____'
     *
     * @param word secret word
     * @return String with only underscores
     */
    public static String generateHiddenWord(String word) {
        return "_".repeat(word.length());
    }


    public static String selectRandomWord(Random random, String[] words) {
        // words ma v tejto chvili v podstate neznamu velkost
        // my chceme z toho pola si ziskat random prvok
        return words[random.nextInt(words.length)];
    }
}