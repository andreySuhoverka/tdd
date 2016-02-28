package epam.hangman;

public class HangmanGame {
    
    private int level;
    
    public void setLevel(int level) {
        if(level < 3 || level > 7){
            throw new IllegalArgumentException("Level must be between 3 and 7");
        }
        this.level = level;        
    }
    
    public int getLevel() {
        return level;
    }
    
}
