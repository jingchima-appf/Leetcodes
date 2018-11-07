package thumbtackPrepare;

import org.junit.BeforeClass;
import org.junit.Test;
import utils.TreeNode;

import java.util.Arrays;

import static org.junit.Assert.*;
public class AmicablePariTest {

    private static final AmicablePair instance = new AmicablePair();
    @Test
    public void testAmicablePair() {
        for (int k = 300; k <= 1000; k++) {
            assertEquals(instance.correctAnswer(k), instance.getAmicablePairs(k));
        }
    }
}
