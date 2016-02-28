package epam.hangman;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HangmanTest {
    
    @Test
    public void userShouldBeAbleToChooseTheLevel() {
        HangmanGame hangman = new HangmanGame();
        hangman.setLevel(6);
        assertEquals("Level must be 6", 6, hangman.getLevel());
    }
    

}
