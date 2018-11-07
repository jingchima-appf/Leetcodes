package IXL;

import IXL.prepare.MinNumbersToGetTarget;
import IXL.prepare.RecursionInFraction;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
public class RecursionInFractionTest {
    private static RecursionInFraction instance;
    @BeforeClass
    public static void setup() {
        instance = new RecursionInFraction();
    }
    @Test
    public void testNoRepeating() {
        assertEquals("0",instance.findDuplicateInFraction(2));
        assertEquals("0", instance.findDuplicateInFraction(4));
    }

    @Test
    public void testRepeating1() {
        assertEquals("3",instance.findDuplicateInFraction(3));
    }

    @Test
    public void testRepeating2() {
        assertEquals("142857",instance.findDuplicateInFraction(7));
    }
}
