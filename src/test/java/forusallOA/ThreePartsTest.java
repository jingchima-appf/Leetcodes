package forusallOA;

import org.junit.Test;

import static org.junit.Assert.*;

public class ThreePartsTest {
    @Test
    public void test() {
        assertEquals(true, ThreeParts.threeParts(new int[] {3,1,7,5,15,16,4,6,3,7,0}));
        assertEquals(false, ThreeParts.threeParts(new int[] {3,1,7,5,15,16,4,6,3,7,1}));
    }
}