package IXL;

import IXL.prepare.StickersToSpellWord;
import org.junit.Test;
import static org.junit.Assert.*;

public class StickersToSpellWordTest {

    @Test
    public void testMinStickers() {
        StickersToSpellWord instance = new StickersToSpellWord();
        assertEquals(3, instance.minStickersII(new String[] {"with","example","science"}, "thehat"));
    }
}
