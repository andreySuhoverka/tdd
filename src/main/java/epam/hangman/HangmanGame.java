package epam.hangman;

import java.util.HashMap;
import java.util.Map;

public class HangmanGame {

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
    
    public void setLevel(int level) {
        if(level < 3 || level > 7){
            throw new IllegalArgumentException("Level must be between 3 and 7");
        }
        this.level = level;        
    }
    
    public int getLevel() {
        return level;
    }
    
    private void setDefaultLevel(){
        level = 3;
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

}
