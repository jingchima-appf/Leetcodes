package forusallOA;

import org.junit.Test;

import static org.junit.Assert.*;

public class CorrectParenthesesTest {

    @Test
    public void test() {
        String input = "))())((((";
        String expected = "((())())(((())))";
        assertEquals(expected, CorrectParentheses.correctParentheses(input));
    }
}