package strengthVII;

import org.junit.Test;

import static org.junit.Assert.*;

public class LongestIncreasingTest {
    @Test
    public void longestIncreasingSubarray() throws Exception {
        int[] input = {1, 3, 4, 5, 2, 3, 10, 5, 7, 9, 0};
        assertEquals(4, LongestIncreasing.longestIncreasingSubarray(input));
    }

    @Test
    public void longestIncreasingSubsequence() {
        int[] input = {1,3,6,7,9,4,10,5,6};
        assertEquals(6, LongestIncreasing.longestIncreasingSubsequence(input));
    }

}