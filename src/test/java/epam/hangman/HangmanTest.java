package epam.hangman;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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

    @Test
    public void userCanObtainWordAccordingToTheLevel(){
        assertEquals("Word must have size of four", 4, hangman.obtainWordAccordingToTheLevel(4).length());
        assertEquals("Word must have size of five",5, hangman.obtainWordAccordingToTheLevel(5).length());
        assertEquals("Word must have size of six", 6, hangman.obtainWordAccordingToTheLevel(6).length());
        assertEquals("Word must have size of seven", 7, hangman.obtainWordAccordingToTheLevel(7).length());
    }
    
    @Test
    public void userCanGuessLetter() {
        assertFalse(hangman.guessOneLetter("ж"));
        assertTrue(hangman.guessOneLetter("я"));
    }
    
    @Test(expected = RuntimeException.class)
    public void userCanMakeNotMoreThanSevenSteps() {
        for(int k = 0; k < 10; k++) {
            hangman.guessOneLetter("я");            
        }        
    }

    @Test
    public void hangmanGameCanCheckWeatherUserAliveOrNot() {
        assertTrue("User must be alive because he didn't make steps yet", hangman.userAlive());
        for(int k = 0; k < 4; k++) {
            hangman.guessOneLetter("з");
        }
        assertFalse("User must not be alive", hangman.userAlive());
    }

}
