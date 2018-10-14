package DPII;

import java.util.ArrayList;
import java.util.List;


// Question 1: subarray with the maximum sum
public class SubarrayWithLargestSum {

    // DP[i]: the maximum sum for the subarrays which ends at index i
    // base case : DP[0] = nums[0]
    // induction rule: DP[i] = max { DP[i-1] + nums[i], nums[i] }
    // to return the start and end index, maintain the following variables
    // 1. curLeft
    // 2. globalLeft and globalRight: the left and right index for global max array
    public List<Integer> getMaxSubarray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        int dp = nums[0];
        int curLeft = 0;
        int max = Integer.MIN_VALUE;
        int globalLeft = 0;
        int globalRight = 0;
        for (int i = 1; i < nums.length; i++) {
            if (dp < 0) {
                dp = nums[i];
                curLeft = i;
            } else {
                dp += nums[i];
            }
            if (max < dp) {
                max = dp;
                globalLeft = curLeft;
                globalRight = i;
            }
        }
        List<Integer> results = new ArrayList<>();
        results.add(globalLeft);
        results.add(globalRight);
        return results;
    }

    public static void main(String[] args) {
        SubarrayWithLargestSum instance = new SubarrayWithLargestSum();
        System.out.println(instance.getMaxSubarray(new int[] { 1, 2, 3, -1, -2, 10, -100}));
    }
}
