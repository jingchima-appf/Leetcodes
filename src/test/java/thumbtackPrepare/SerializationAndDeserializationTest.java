package thumbtackPrepare;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SerializationAndDeserializationTest {

    private static final SerializationAndDeserialization instance = new SerializationAndDeserialization();
    private static TreeNode root;

    @BeforeClass
    public static void setup() {
        root = new TreeNode(0);
        List<TreeNode> nodes = new ArrayList<>();
        nodes.add(root);
        for (int i = 1; i <= 13; i++) {
            nodes.add(new TreeNode(i));
        }
        root.children.add(nodes.get(1));
        root.children.add(nodes.get(2));
        root.children.add(nodes.get(3));
        nodes.get(1).children.add(nodes.get(4));
        nodes.get(1).children.add(nodes.get(5));
        nodes.get(2).children.add(nodes.get(6));
        nodes.get(3).children.add(nodes.get(7));
        nodes.get(3).children.add(nodes.get(8));
        nodes.get(3).children.add(nodes.get(9));
        nodes.get(5).children.add(nodes.get(10));
        nodes.get(7).children.add(nodes.get(11));
        nodes.get(7).children.add(nodes.get(12));
    }

    @Test
    public void testSerialize() {
        String res = instance.serialize(root);
        String expected = "0,3,1,2,2,1,3,3,4,0,5,1,6,0,7,2,8,0,9,0,10,0,11,0,12,0,";
        assertEquals(expected, res);
    }


    @Test
    public void testDeserialize() {
        String tree = "0,3,1,2,2,1,3,3,4,0,5,1,6,0,7,2,8,0,9,0,10,0,11,0,12,0,";
        TreeNode res = instance.deserialize(tree);

        assertEquals(root, res);
    }

}