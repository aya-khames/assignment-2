import java.util.Scanner;

public class main {
    public static void main(String[] args) throws Exception{
        Hangman test = new Hangman();
        test.setMaxWrongGuesses(5);
        System.out.println("Guess a character");
        Scanner scan = new Scanner(System.in);
        Character c = scan.next().charAt(0);
        String word = test. guess(c);
    }
}