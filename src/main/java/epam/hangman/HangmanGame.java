package epam.hangman;

import java.io.Console;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HangmanGame {

    public static void main(String[] args) {
        new HangmanGame().startGame();
    }

    HangmanGame() {
        setDefaultLevel();
        levelToWord = new HashMap<Integer, String>(){
            {
                put(3,"ярд");                
                put(4,"риск");                
                put(5,"музон");                
                put(6,"ролики");                
                put(7,"подарок");                
            }
        };
        madeSteps = 0;
    }
    
    private Map<Integer, String> levelToWord;
    
    private int level;
    private int madeSteps;
    private String actualWord;
    
    public void setLevel(int level) {
        if(level < 3 || level > 7){
            throw new IllegalArgumentException("Level must be between 3 and 7");
        }
        this.level = level;
        actualWord = createInitialWord(level);

    }

    private String createInitialWord(int level) {
        String result = "";
        for(int k = 0; k < level; k++) {
            result += "_";
        }
        return result;
    }
    
    public int getLevel() {
        return level;
    }
    
    private void setDefaultLevel(){
        setLevel(3);
    }

    public String obtainWordAccordingToTheLevel(int level) {
        return levelToWord.get(level);
    }

    public boolean guessOneLetter(String letter) {
        madeSteps++;
        if(madeSteps == 8){
            throw new RuntimeException("game over");
        }
        if(letter.length() != 1){
            throw new IllegalArgumentException("user can type only one letter");
        }
        String word = obtainWordAccordingToTheLevel(level);
        return word.contains(letter);
    }

    public boolean userAlive() {
        return madeSteps < level + 1;
    }

    public void startGame(){
        Console console = System.console();
        if(console == null){
            throw new RuntimeException("There is no console available!");
        }
        System.out.print("Choose word length: ");
        String level = console.readLine();
        setLevel(Integer.valueOf(level));
        while(needNextStep()){
            System.out.print("Guess letter: ");
            String letter = console.readLine();
            boolean guessed = this.guessOneLetter(letter);
            handleActualWord(letter, guessed);
            showCurrentGameStatus();
            System.out.println();
        }
        showGameResult();
    }

    private void showGameResult() {
        if(!actualWord.contains("_")){
            System.out.println("Winner, you are alive!");
        } else {
            System.out.println("unfortunately you died :(");
        }
    }

    private void showCurrentGameStatus() {
        System.console().printf("Current word is: " + actualWord);
    }

    private void handleActualWord(String letter, boolean guessed) {
        if(guessed) {
            actualWord = updateActualWordWithLetter(letter);
        }

    }

    private String updateActualWordWithLetter(String letter) {
        String word = obtainWordAccordingToTheLevel(getLevel());
        List<Integer> guessedPositions = new ArrayList<Integer>();
        for (int k = 0; k < word.length(); k++) {
            if(word.substring(k, k + 1).equals(letter)) {
                guessedPositions.add(k);
            }
        }
        String updatedWord = "";
        for (int k = 0; k < word.length(); k++) {
            if(guessedPositions.contains(k)) {
                updatedWord += letter;
            }else {
                updatedWord += actualWord.charAt(k);
            }
        }
        return updatedWord;
    }

    private boolean needNextStep() {
        return userAlive() && actualWord.contains("_");
    }

}
