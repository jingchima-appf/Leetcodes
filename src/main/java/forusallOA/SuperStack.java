package forusallOA;

import java.util.ArrayList;
import java.util.List;

public class SuperStack {

    final List<Integer> stack;

    public SuperStack() {
        stack = new ArrayList<>();
    }

    public Integer pop() {
        if (stack.size() == 0) {
            return null;
        }
        return stack.remove(stack.size() - 1);
    }

    public void push(int e) {
        stack.add(e);
    }

    // assumption: k <= stack.size()
    public void inc(int k, int e) {
        for (int i = 0; i < k; i++) {
            stack.set(i, stack.get(i) + e);
        }
    }
}
