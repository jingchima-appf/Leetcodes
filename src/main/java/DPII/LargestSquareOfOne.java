package DPII;

public class LargestSquareOfOne {


    // dp[i][j]: the length of maximum one square, among all one squares whose bottom-right corner is at (i, j)
    // base case: dp[0][j] = 1 or 0, dp[i][0] = 1 or 0
    // induction rule: dp[i][j] = min { dp[i-1][j], dp[i][j-1], dp[i-1][j-1] } + 1;
    // also needs to keep track of a global max;
    public int getLargestSquareOne(int[][] matrix) {

        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];
        int max = 0; // global max length

        for (int j = 0; j < n; j++) {
            dp[0][j] = matrix[0][j] == 0 ? 0 : 1;
            max = Math.max(max, dp[0][j]);
        }
        for (int i = 0; i < m; i++) {
            dp[i][0] = matrix[i][0] == 0 ? 0 : 1;
            max = Math.max(max, dp[i][0]);
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) + 1;
                }
                max = Math.max(max, dp[i][j]);
            }
        }

        return max * max;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1, 1, 1}, {0, 1, 1}, {1, 0, 1}};
        LargestSquareOfOne instance = new LargestSquareOfOne();
        System.out.println(instance.getLargestSquareOne(matrix));
    }
}
