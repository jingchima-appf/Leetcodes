package IXL;


import IXL.prepare.MinNumbersToGetTarget;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class MinNumbersToGetTargetTest {

    @Test
    public void canBreakIntoWords() {
        Set<String> set = new HashSet<>();
        set.add("Good");

    }

    @Test
    public void testMinWords() {
        MinNumbersToGetTarget instance = new MinNumbersToGetTarget();
        List<String> dict = new ArrayList<>();
        dict.add("a");
        dict.add("b");
        dict.add("ab");
        assertEquals(1, instance.minWords(dict, "ab"));
    }
}
