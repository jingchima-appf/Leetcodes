package strengthVII;

import org.junit.Test;

import static org.junit.Assert.*;

public class PredictWinnerTest {
    @Test
    public void predictTheWinner() throws Exception {
        int[] input = {1,567,1,1,99,100};
        assertEquals(true, PredictWinner.PredictTheWinner(input));
    }

}