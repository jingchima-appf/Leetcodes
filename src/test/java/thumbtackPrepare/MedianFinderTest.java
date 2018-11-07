package thumbtackPrepare;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;
public class MedianFinderTest {

    private static MedianFinder instance = new MedianFinder();
    private static MedianFinderII instanceII = new MedianFinderII();

    @BeforeClass
    public static void setup() {

    }

    @Test
    public void testGetMedian() {
        int[] nums = {1, 2, 3, 10, 24, 6, -18, 20};
        double[] medians = {1, 1.5, 2, 2.5, 3, 4.5, 3, 4.5};
        for (int i = 0; i < nums.length; i++) {
            instance.addNum(nums[i]);
            assertEquals(medians[i], instance.getMedian(), 0.00001);
        }
    }

    @Test
    public void testMedianFinderII() {
        int[] nums = {1, 2, 3, 10, 24};
        double[] medians = {1, 1.5, 2, 2.5, 3};
        double[] means = {1, 1.5, 2, 4, 8};
        for (int i = 0; i < nums.length; i++) {
            instanceII.addNum(nums[i]);
            assertEquals(medians[i], instanceII.getMedian(), 0.00001);
            assertEquals(means[i], instanceII.getMean(), 0.000001);
        }
    }
}
