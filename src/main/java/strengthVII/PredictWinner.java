package strengthVII;

public class PredictWinner {
    public static boolean PredictTheWinner(int[] nums) {
        if (nums.length == 0) {
            return true;
        }
        int[][] dp = new int[nums.length][nums.length];
        for (int i = 0; i < nums.length; i++) {
            dp[i][i] = nums[i];
        }
        for (int i = 0; i + 1 < nums.length; i++) {
            dp[i][i+1] = Math.max(nums[i], nums[i+1]);
        }
        for (int i = nums.length - 3; i >= 0; i--) {
            for (int j = nums.length - 1; j >= i + 2; j--) {
                dp[i][j] = Math.max(nums[i] + Math.min ( dp[i+1][j-1], dp[i+2][j] ),
                        nums[j] + Math.min ( dp[i][j-2], dp[i+1][j-1] )
                );
            }
        }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        System.out.println(sum);
        System.out.println(dp[0][nums.length-1]);
        return dp[0][nums.length-1] >= (sum + 1) / 2;
    }
}
