package stacks;

import java.util.ArrayDeque;
import java.util.Deque;

public class LargestRectangleInHistogram {
    public int largestRectangleArea(int[] heights) {
        if (heights.length == 0) {
            return 0;
        }
        Deque<Integer> stack = new ArrayDeque<>();
        stack.offerFirst(0);
        int max = heights[0];
        for (int i = 1; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[i] <= heights[stack.peek()]) {
                int cur = stack.pollFirst();
                int left = stack.isEmpty() ? -1 : stack.peek();
                max = Math.max(max, (i - left - 1) * heights[cur]);
            }
            stack.offerFirst(i);
        }
        if (stack.isEmpty()) {
            return max;
        }
        int right = stack.peek() + 1;
        while (!stack.isEmpty()) {
            int cur = stack.pollFirst();
            int left = stack.isEmpty() ? -1 : stack.peek();
            max = Math.max(max, (right - left - 1) * heights[cur]);
        }
        return max;
    }


    public static void main(String[] args) {
        LargestRectangleInHistogram largestRectangleInHistogram = new LargestRectangleInHistogram();
        System.out.println(largestRectangleInHistogram.largestRectangleArea(new int[] {2,1,5,6,2,3}));
    }
}
