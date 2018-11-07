package StrengthenI;

import java.util.Arrays;

public class ArrayDeduplication {
    // Question 1: sorted array, same elements only retain 2
    // assumption: nums is sorted.
    public int[] deduplicateII(int[] nums) {
        int slow = 2;
        int fast = 2;
        while (fast < nums.length) {
            if (nums[fast] == nums[slow-1] && nums[fast] == nums[slow-2]) { // actually we only need to compare with nums[slow - 2] because the input is sorted
                fast++;
            } else {
                nums[slow] = nums[fast];
                slow++;
                fast++;
            }
        }
        return Arrays.copyOf(nums, slow);
    }

    // Question 3: sorted array, same elements retain 0

    public int[] deduplicateIII(int[] nums) {
        int slow = 0;
        int fast = 0;
        while (fast < nums.length) {
            int nextFast = fast;
            while (nextFast < nums.length && nums[nextFast] == nums[fast]) {
                nextFast++;
            }
            if (nextFast - fast == 1) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast = nextFast;
        }
        return Arrays.copyOf(nums, slow);
    }

    // Question 4: repeatedly remove duplicates

    public int[] deduplicateIV(int[] nums) {
        int slow = 0;
        int fast = 0;
        while (fast < nums.length) {
            if (slow != 0 && nums[slow - 1] == nums[fast]) {
                fast++;
                while (fast < nums.length && nums[fast] == nums[fast-1]) {
                    fast++;
                }
                slow--;
            } else {
                nums[slow] = nums[fast];
                slow++;
                fast++;
            }
        }
        return Arrays.copyOf(nums, slow);
    }


}
