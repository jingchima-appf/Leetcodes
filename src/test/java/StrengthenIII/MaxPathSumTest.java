package StrengthenIII;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;
public class MaxPathSumTest {
    private static MaxPathSum instance = new MaxPathSum();
    private static TreeNode root;

    @BeforeClass
    public static void setup() {
        root = new TreeNode(-1);
        root.left = new TreeNode(-2);
        root.right = new TreeNode(-3);
    }
    @Test
    public void testMaxSum() {
        int max = instance.maxSumIII(root);
        assertEquals(-3, max);
    }
}
