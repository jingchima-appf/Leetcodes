package thumbtackPrepare;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;
public class ClosestPalindromeTest {

    private static final ClosestPalindrome instance = new ClosestPalindrome();

    @Test
    public void testClosestPalindrome() {
        System.out.println(instance.nearestPalindromic("825846317156433920"));
    }
}
