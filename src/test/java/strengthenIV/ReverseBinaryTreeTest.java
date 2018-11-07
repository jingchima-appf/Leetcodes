package strengthenIV;

import org.junit.BeforeClass;
import org.junit.Test;
import utils.TreeNode;

import java.util.Arrays;

import static org.junit.Assert.*;
public class ReverseBinaryTreeTest {

    private final static ReverseBinaryTree instance = new ReverseBinaryTree();
    @Test
    public void testReverseTree() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        assertEquals("4 \n5 2 \nnull null 3 1 \nnull null null null \n", instance.reverseTree(root).toString());
    }
}
