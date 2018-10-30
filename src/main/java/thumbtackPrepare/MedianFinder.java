package thumbtackPrepare;

import java.util.Collections;
import java.util.PriorityQueue;

public class MedianFinder {
    // 1  3             2
    // 1  (1 + 3) / 2   2

    // heap: dynamic max
    // heap


    // maxHeap   size of minHeap <= size of maxHeap <= size of minHeap + 1
    // minHeap

    // getMedian: median = maxHeap.peek() or avg of two peek elements from two heaps

    private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    private PriorityQueue<Integer> minHeap = new PriorityQueue<>();

    public double getMedian() {
        if (maxHeap.size() == minHeap.size()) {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        } else {
            return maxHeap.peek();
        }
    }

    public void addNum(int num) {
        if (maxHeap.isEmpty()) {
            maxHeap.offer(num);
        } else {
            if (maxHeap.peek() >= num) {
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
        }
    }
}

class MedianFinderII {
    // int[10000] nums, num[i]: the # of number i.
    // each time update the nums array
    // size
    // sum
    // median: size / 2 -> time complexity: O(1)
    // mean: sum / size

    private int[] nums;
    private int sum;
    private int size;

    MedianFinderII() {
        nums = new int[10000];
        sum = 0;
        size = 0;
    }

    public void addNum(int num) {
        nums[num]++;
        sum += num;
        size++;
    }

    public double getMedian() {
        if (size % 2 == 0) {
            return (getKth((size - 1) / 2) + getKth(size / 2)) / 2.0;
        } else {
            return getKth(size / 2);
        }
    }

    public double getMean() {
        return sum * 1.0 / size;
    }

    private int getKth(int k) { // input k is the index
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            count += nums[i];
            if (count >= k + 1) {
                return i;
            }
        }
        return -1; // invalid number
    }
}
