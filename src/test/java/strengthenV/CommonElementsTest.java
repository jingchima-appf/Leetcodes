package strengthenV;

import org.junit.Test;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CommonElementsTest {

    private static final CommonElements instance = new CommonElements();

    @Test
    public void testCase1() {
        int[] array1 = {1, 3, 3, 3, 7, 10, 15};
        int[] array2 = {3, 15};
        List<Integer> expectedResults = new ArrayList<>();
        expectedResults.add(3);
        expectedResults.add(15);
        assertEquals(expectedResults, instance.case1(array1, array2));
    }

    @Test
    public void testCase2() {
        int[] array1 = {1, 3, 3, 3, 7, 10, 15};
        int[] array2 = {3, 15};
        List<Integer> expectedResults = new ArrayList<>();
        expectedResults.add(3);
        expectedResults.add(15);
        assertEquals(expectedResults, instance.case2(array1, array2));
    }

    @Test
    public void testCase3() {
        int[] array1 = {1, 3, 7, 10, 11, 3, 3, 7, 10, 15};
        int[] array2 = {3, 3, 100, 20, 3, 15, 15};
        List<Integer> expectedResults = new ArrayList<>();
        expectedResults.add(3);
        expectedResults.add(15);
        assertEquals(expectedResults, instance.case3(array1, array2));
    }

    @Test
    public void testCommonElementsInTwoArray() {
        List<Integer> param1 = new ArrayList<>();
        param1.add(1);
        param1.add(2);
        param1.add(2);
        param1.add(3);
        param1.add(3);
        param1.add(3);
        param1.add(10);
        int[] array2 = {2, 3};
        List<Integer> expectedResults = new ArrayList<>();
        expectedResults.add(2);
        expectedResults.add(3);
        instance.commonElementsInTwoArray(param1, array2);
        System.out.println(param1);
        assertEquals(expectedResults, param1);
    }

    @Test
    public void testCommonElements() {
        int[][] arrays = {{1, 2, 2, 2, 3, 3, 3, 10, 11, 15}, {2, 3, 10, 11, 14, 16, 19}, {2, 3, 11, 14}};
        List<Integer> expectedResults = new ArrayList<>();
        expectedResults.add(2);
        expectedResults.add(3);
        expectedResults.add(11);
        assertEquals(expectedResults, instance.commonElements(arrays));
    }
}