package stream;

import java.util.Collections;
import java.util.PriorityQueue;

public class GetMedian {
    // invariants: 1. maxHeap < minHeap 2. minHeap.size() <= maxHeap.size() <= minHeap.size() + 1
    private PriorityQueue<Integer> maxHeap;
    private PriorityQueue<Integer> minHeap;

    public GetMedian() {
        maxHeap = new PriorityQueue<>(Collections.reverseOrder()); // forgot to use reverseOrder()
        minHeap = new PriorityQueue<>();
    }

    public double getMedian(int num) {
        if (maxHeap.isEmpty()) {
            maxHeap.offer(num);
            return num;
        }
        if (num <= maxHeap.peek()) { // forgot num <= maxHeap.peek()
            maxHeap.offer(num);
            if (maxHeap.size() > minHeap.size() + 1) {
                minHeap.offer(maxHeap.poll());
            }
        } else {
            minHeap.offer(num);
            if (minHeap.size() > maxHeap.size()) {
                maxHeap.offer(minHeap.poll());
            }
        }
        if (maxHeap.size() == minHeap.size()) {
            return (maxHeap.peek() + minHeap.peek()) / 2.0; // forgot / 2.0 instead of / 2
        } else {
            return maxHeap.peek();
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5,6,7,8,9,10};
        GetMedian g = new GetMedian();
        for (int num : nums) {
            System.out.print(g.getMedian(num) + " ");
        }
        // 1 1.5 2 2.5, 3, 3.5, 4, 4.5, 5, 5.5
        System.out.println();
    }
}
