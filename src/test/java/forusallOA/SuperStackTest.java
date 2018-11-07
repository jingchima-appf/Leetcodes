package forusallOA;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SuperStackTest {

    private SuperStack stack;
    @Before
    public void setUp() throws Exception {
        stack = new SuperStack();
    }

    @Test
    public void test() {
        List<Integer> results = new ArrayList<>();
        stack.push(4);
        results.add(4);
        assertEquals(results, stack.stack);
        stack.pop();
        results.remove(results.size() - 1);
        assertEquals(results, stack.stack);
        stack.push(3);
        results.add(3);
        stack.push(5);
        results.add(5);
        stack.push(2);
        results.add(2);

        assertEquals(results, stack.stack);

        stack.inc(3, 1);
        for (int i = 0; i < 3; i++) {
            results.set(i, results.get(i) + 1);
        }

        assertEquals(results, stack.stack);

        stack.pop();
        results.remove(results.size() - 1);

        assertEquals(results, stack.stack);

        stack.push(1);
        results.add(1);
        assertEquals(results, stack.stack);

        System.out.println(stack.stack);
        System.out.println(results);

        stack.inc(2,2);
        for (int i = 0; i < 2; i++) {
            results.set(i, results.get(i) + 2);
        }
        assertEquals(results, stack.stack);

        stack.push(4);
        results.add(4);
        assertEquals(results, stack.stack);

        System.out.println(stack.stack);

    }

}