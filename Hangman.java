import eg.edu.alexu.csd.datastructure.hangman.cs02.IHangman;

import java.io.*;
import java.util.Random;
import java.io.File;
import java.lang.*;
import java.util.Scanner;

public class Hangman implements IHangman {
    int maxGuesses;
    public String[] readFile(){
        String[] words = new String[20];
        try{
            File file = new File("C:\\Users\\Lenovo\\IdeaProjects\\Lab2\\Dictionary.txt");
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String get;
            int counter = 0;
            while ((get = reader.readLine()) != null){
                words[counter]=get;
                counter++;
            }
            reader.close();
            return words;
        }catch(IOException e){
            return null;
        }
    }
    public void setDictionary(String[] words){
        words = readFile();
    }
    public String selectRandomSecretWord(){
        Random random = new Random();
        int index = random.nextInt(20);
        String[] words = readFile();
        return words[index];
    }

    public String guess(Character c) throws IOException{
        int wrong = 0;
        int i = 0;
        String secretWord = selectRandomSecretWord();
        int unknownBefore = secretWord.length();
        int unknownAfter = unknownBefore;
        char[] ch = new char[secretWord.length()];
        char[] secret = new char[secretWord.length()];
        for (i = 0; i < secretWord.length(); i++){
            ch[i] = '_';
            secret[i] = secretWord.charAt(i);
        }
        String guessed = new String(ch);
        while (wrong<maxGuesses){
            for (i = 0; i < secretWord.length(); i++){
                if(Character.toLowerCase(c) == secret[i]){
                    ch[i] = secret[i] ;
                    unknownAfter--;
                }
            }
            guessed = new String(ch);
            System.out.println(guessed);
            if (unknownAfter < unknownBefore){
                unknownBefore = unknownAfter;
                if(unknownAfter==0){
                    System.out.println("WINNER");
                    break;
                }else {
                    Scanner scan = new Scanner(System.in);
                    c = scan.next().charAt(0);
                }
            }else if (unknownAfter != 0){
                wrong++;
                System.out.println("Guess again");
                Scanner scan = new Scanner(System.in);
                c = scan.next().charAt(0);
            }
        }
        if(unknownAfter != 0){
            System.out.println("LOSER");
            System.out.println(secretWord);
        }
        return guessed;
    }
    public void setMaxWrongGuesses(Integer max){
        maxGuesses=max;
    }
}