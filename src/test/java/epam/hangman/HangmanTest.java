package epam.hangman;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HangmanTest {

    HangmanGame hangman;
    
    @Before
    public void setUpHangman() {
        hangman = new HangmanGame();
    }
    
    @Test
    public void userShouldBeAbleToChooseTheLevel() {
        hangman.setLevel(6);
        assertEquals("Level must be 6", 6, hangman.getLevel());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void userShouldNotBeAbleToChooseLevelLessThanThree() {
        hangman.setLevel(2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void userShouldNotBeAbleToChooseLevelHigherThanSeven() {        
        hangman.setLevel(8);
    }
    
    @Test
    public void defaultLevelMustThree() {
        assertEquals("Default level must be three", 3, hangman.getLevel());        
    }
    

}
