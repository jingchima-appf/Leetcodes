package sixSensePrepare;

import org.junit.Test;

import static org.junit.Assert.*;

public class TreeNodeTest {
    @Test
    public void toStringTest() throws Exception {
        TreeNode root = new TreeNode("1");
        root.children.add(new TreeNode("2"));
        root.children.add(new TreeNode("3"));
        System.out.println(root.toString());
        assertEquals("1\n- 2\n- 3\n", root.toString());
    }

}