package sixSensePrepare;

import org.junit.Test;

import static org.junit.Assert.*;

public class BuildTreeTest {
    @Test
    public void buildTreeTest() throws Exception {
        BuildTree instance = new BuildTree();
        String[] input = {"<xml>", "<nodeA>", "AA", "<nodeAA>", "WOQU", "</nodeAA>", "</nodeA>", "<nodeB>", "BB", "</nodeB>", "</xml>"};
        TreeNode root = new TreeNode("");
        root.children.add(new TreeNode("AA"));
        root.children.add(new TreeNode("BB"));

        String expected = "<xml>\n- AA\n-- WOQU\n- BB\n";
        TreeNode res = instance.buildTree(input);
        System.out.println(res);
        assertEquals(expected, res.toString());
    }

}