package findKthElements;

import java.util.Arrays;

public class FindKthElements {

    // General Solution: quick select

    // Question1: find the kth smallest elements in the array
    public int kthSmallestElement(int[] nums, int k) {
        return quickSelect(nums, k, 0, nums.length);
    }

    /*
    start: inclusive, end: exclusive
     */
    private int quickSelect(int[] nums, int k, int start, int end) { // k starts from 0
        if (start >= end - 1) {
            return nums[k];
        }
        // [0, 1) * (end - start) = [0, end - start) + start = [start, end)
        int pivot = nums[ (int) (Math.random() * (end - start) + start) ];
        // left: <= pivot  right: => pivot
        int left = start;
        int right = end - 1;
        while (left <= right) {
            while (left <= right && nums[left] < pivot) {
                left++;
            }
            while (left <= right && nums[right] > pivot) {
                right--;
            }
            if (left <= right) {
                swap(nums, left, right);
                left++;
                right--;
            }
        }
        // ... , right, (?), left, ...
        // [start, right]: <= pivot
        // [left, end): > pivot
        if (k <= right) {
            return quickSelect(nums, k, start, right + 1);
        } else if (k >= left) {
            return quickSelect(nums, k, left, end);
        }
        return nums[k];
    }

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }


    // Question 2: find the 95%  有可能存在没有恰好95%的情况
    // i.e. find the minimum number, such that the number of numbers less than or equal to it, is greater than 95%

    // Method I: quick-select

    public int findPercent(double percent, int[] nums) {

        int idx = (int) Math.ceil(nums.length * percent) - 1; // calculate the expected index, and then use quick-select
        return quickSelect(nums, idx, 0, nums.length);
    }

    // Method II: if we know the range of all the numbers, then we can use bucket sort
    // 因为我们知道答案一定在这个区间里面
    // max and min both inclusive
    public int findPercent(int[] nums, int min, int max, double percent) {
        int length = max - min + 1;
        int[] counts = new int[length];
        fill(counts, nums, min);
        int count = 0;
        for (int i = 0; i < counts.length; i++) {
            count += counts[i];
            if (count * 1.0 / nums.length >= percent) {
                return i + min;
            }
        }
        return Integer.MIN_VALUE;
    }

    private void fill(int[] counts, int[] nums, int min) {
        for (int num : nums) {
            counts[num - min]++;
        }
    }

    public static void main(String[] args) {

        FindKthElements findKthElements = new FindKthElements();

        int[] nums = {4, 2, 2, 1 ,2 ,5, 3, 5, 10 ,6};
        int[] sorted = nums.clone();
        Arrays.sort(sorted);
        System.out.println(findKthElements.kthSmallestElement(nums, 5));
        System.out.println(sorted[5]);

        // 1 2 2 2 3 4 5 5 6 10
        System.out.println(findKthElements.findPercent(0.7, nums));
        System.out.println(findKthElements.findPercent(nums, 1, 10, 0.7));

    }
}
