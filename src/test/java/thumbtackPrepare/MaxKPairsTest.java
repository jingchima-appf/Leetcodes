package thumbtackPrepare;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MaxKPairsTest {
    @Test
    public void maxKSums() throws Exception {
        int[] nums = {3, 7, 10, 3, 5, 20, 10, 8};
        // 6, 8, 23, 13, 11
        // 12 27 17 15
        // 30 20 18
        // 13 11
        // 13
        int l = 3;
        int k = 4;
        List<Integer> expected = new ArrayList<>();
        expected.add(30);
        expected.add(27);
        expected.add(23);
        expected.add(20);
        assertEquals(expected, MaxKPairs.maxKSums(nums, l, k));
    }

}