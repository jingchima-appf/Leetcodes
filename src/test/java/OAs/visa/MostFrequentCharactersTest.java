package OAs.visa;

import org.junit.Test;

import static org.junit.Assert.*;

public class MostFrequentCharactersTest {
    @Test
    public void mostFrequentCharacterTest1() throws Exception {
        String input = "good";
        char expected = 'o';
        assertEquals(expected, MostFrequentCharacters.mostFrequentCharacter(input));
    }
    @Test
    public void mostFrequentCharacterTest2() throws Exception {
        String input = "oodd";
        char expected = 'o';
        assertEquals(expected, MostFrequentCharacters.mostFrequentCharacter(input));
    }
}