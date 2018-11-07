package StrengthenI;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ArrayDeduplicationTest {

    private static ArrayDeduplication instance;

    @BeforeClass
    public static void setup() {
        instance = new ArrayDeduplication();
    }

    @Test
    public void testII() {
        int[] nums = {1, 1, 1, 1, 1, 2, 2, 2, 3, 3, 3};
        assertArrayEquals(new int[] {1, 1, 2, 2, 3, 3}, instance.deduplicateII(Arrays.copyOf(nums, nums.length)));

        int[] nums2 = {1, 2, 3, 3, 3};
        assertArrayEquals(new int[] {1, 2, 3, 3}, instance.deduplicateII(nums2));
    }

    @Test
    public void testIII() {
        int[] nums = {1, 1, 1, 1, 1, 2, 2, 2, 3, 3, 3};
        assertArrayEquals(new int[] {}, instance.deduplicateIII(nums));
        int[] nums2 = {1, 2, 3, 3, 4, 4, 6};
        assertArrayEquals(new int[] {1, 2, 6}, instance.deduplicateIII(nums2));
        int[] nums3 = {1, 1};
        assertArrayEquals(new int[] {}, instance.deduplicateIII(nums3));
    }

    @Test
    public void testIV() {
        int[] nums = {1, 2, 2, 1, 0, 1, 1, 2};
        assertArrayEquals(new int[] {0, 2}, instance.deduplicateIV(nums));
    }
}
