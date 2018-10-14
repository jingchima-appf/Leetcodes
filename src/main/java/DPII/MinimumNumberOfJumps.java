package DPII;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Question 0: Minimum number of jumps
public class MinimumNumberOfJumps {

    // DP[i]: minimum number of jumps to reach index i. if i is not reachable,
    // DP[i] = -1
    // base case: DP[0] = 0;
    // induction rule: DP[i] = min { DP[j] + 1 } for j = 0: j where j + nums[j] >= i
    public int getJumpNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int[] dp = new int[nums.length];
        dp[0] = 0;
        for (int i = 1; i < nums.length; i++) {
            dp[i] = -1; // initialize as unreachable
            for (int j = 0; j < i; j++) {
                if (dp[j] != -1 && nums[j] + j >= i) {
                    if (dp[i] == -1) {
                        dp[i] = dp[j] + 1;
                    } else {
                        dp[i] = Math.min(dp[i], dp[j] + 1);
                    }
                }
            }
        }
        return dp[nums.length - 1];
    }

    public List<Integer> getPath(int[] nums) {
        int n = nums.length;
        int[] steps = new int[n];
        int[] prevIdx = new int[n];
        steps[0] = 0;
        prevIdx[0] = -1; // 这里设置为 -1 比较好
        for (int i = 1; i < nums.length; i++) {
            steps[i] = -1;
            for (int j = 0; j < i; j++) {
                if (steps[j] != -1 && j + nums[j] >= i) {
                    if (steps[i] == -1 || steps[i] > steps[j] + 1) {
                        steps[i] = steps[j] + 1;
                        prevIdx[i] = j;
                    }
                }
            }
        }
        List<Integer> path = new ArrayList<>();
        int idx = nums.length - 1;
        if (steps[idx] == -1) {
            return path;
        }
        while (idx >= 0) {
            path.add(idx);
            idx = prevIdx[idx];
        }
        Collections.reverse(path);
        return path;
    }

    public static void main(String[] args) {
        MinimumNumberOfJumps instance = new MinimumNumberOfJumps();
        int[] nums = new int[] {1, 2, 2, 0, 2};
        System.out.println(instance.getJumpNumber(nums));
        System.out.println(instance.getPath(nums));
    }
}
