package DPII;


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

    public static void main(String[] args) {
        MinimumNumberOfJumps instance = new MinimumNumberOfJumps();
        System.out.println(instance.getJumpNumber(new int[] {1, 2, 2, 0, 2}));
    }
}
