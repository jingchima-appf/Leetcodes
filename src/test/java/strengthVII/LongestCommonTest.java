package strengthVII;

import DPII.LargestSquareOfOne;
import org.junit.Test;

import static org.junit.Assert.*;

public class LongestCommonTest {
    @Test
    public void longestCommonSubstring() throws Exception {
        String s1 = "hgodod2";
        String s2 = "good";
        assertEquals(2, LongestCommon.longestCommonSubstring(s1, s2));
    }

    @Test
    public void longestCommonSubsequenceTest() {
        String s1 = "1g2o3o4d5";
        String s2 = "go5536od745";
        String expected = "go3od5";
        assertEquals(expected, LongestCommon.longestCommonSubsequence(s1, s2));
    }

}