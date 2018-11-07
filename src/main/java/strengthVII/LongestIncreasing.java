package strengthVII;

import java.util.ArrayList;
import java.util.List;

public class LongestIncreasing {

    public static int longestIncreasingSubarray(int[] nums) {
        int i = 0; // the start of subarray
        int max = 0;
        while (i < nums.length) {
            int j = i;
            while (j < nums.length && (j == i || (nums[j] > nums[j-1]) )) {
                j++;
            }
            max = Math.max(max, j - i);
            i = j;
        }
        return max;
    }

    public static int longestIncreasingSubsequence(int[] nums) {

        int[] dp = new int[nums.length];
        dp[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i] && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                }
            }
        }
        return dp[nums.length - 1];
    }

    public int longestIncreasingSubsequenceII(int[] nums) {

        List<Integer> lengths = new ArrayList<>();
        for (int num : nums) {
            if (lengths.isEmpty() || num > lengths.get(lengths.size() - 1)) {
                lengths.add(num);
            } else {
                int index = binarySearch(lengths, num);
                lengths.set(index, num);
            }
        }
        return lengths.size();
    }

// return the first index of the smallest elements in nums (ascendingly sorted list) which is greater than or equal to num. It’s guaranteed that there will be at least one number in nums which is greater than or equal to num

    private int binarySearch(List<Integer> nums, int num) {
        int left = 0;
        int right = nums.size() - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums.get(mid) >= num) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        if (nums.get(left) >= num) {
            return left;
        }
        return -1; // every elements in nums is strictly less than num
    }



}
