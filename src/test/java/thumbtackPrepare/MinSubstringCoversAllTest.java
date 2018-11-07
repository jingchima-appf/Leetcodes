package thumbtackPrepare;

import org.junit.BeforeClass;
import org.junit.Test;
import utils.TreeNode;

import java.util.Arrays;

import static org.junit.Assert.*;
public class MinSubstringCoversAllTest {
    private static final MinSubstringCoversAll instance = new MinSubstringCoversAll();
    @Test
    public void test() {
        System.out.println(instance.minWindow("ABC", "ABC"));
    }

}
