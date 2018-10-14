package VMwareOA;

import java.util.Arrays;

public class ClimbHills {
    public int minChange(int[] nums) {
        int min = minChangeForIncreasing(nums);
        reverse(nums);
        min = Math.min(min, minChangeForIncreasing(nums));
        return min;
    }

    private void reverse(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

    private void swap(int[] nums, int left, int right) {
        int tmp = nums[left];
        nums[left] = nums[right];
        nums[right] = tmp;
    }

    private int minChangeForIncreasing(int[] nums) {
        // sorted
        // dp[i][j]: the minimum costs for making [0, i] an increasing array, with the maximum <= sorted[j]
        // induction rule: dp[i][j] = min { dp[i-1][j] + abs(nums[i] - sorted[j]), dp[i][j-1] }
        int[] sorted = new int[nums.length];
        System.arraycopy(nums, 0, sorted, 0, nums.length);
        Arrays.sort(sorted);
        int[][] dp = new int[nums.length][nums.length];
        dp[0][0] = sorted[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i][0] = dp[i-1][0] + nums[i] - sorted[0];
        }
        for (int j = 1; j < nums.length; j++) {
            dp[0][j] = Math.min(dp[0][j-1], Math.abs(nums[0] - sorted[j]));
        }
        for (int i = 1; i < nums.length; i++) {
            for (int j = 1; j < nums.length; j++) {
                dp[i][j] = Math.min( dp[i-1][j] + Math.abs(nums[i] - sorted[j]), dp[i][j-1] );
            }
        }
        return dp[nums.length - 1][nums.length - 1];
    }

    public static void main(String[] args) {
        ClimbHills instance = new ClimbHills();
        System.out.println(instance.minChange(new int[] {9, 8, 7, 2, 3, 3}));
    }

}
