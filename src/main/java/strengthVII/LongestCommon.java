package strengthVII;

public class LongestCommon {
    public static int longestCommonSubstring(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        int max = 0;
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else{
                    dp[i][j] = Math.min(dp[i][j-1], dp[i-1][j]);
                }
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }

    public static String longestCommonSubsequence(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i-1) == s2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        StringBuilder res = new StringBuilder();

        // (i, j): the index of dp
        int i = s1.length();
        int j = s2.length();
        while (i > 0 && j > 0) {
            if (dp[i][j] == dp[i-1][j-1] + 1) {
                res.append(s1.charAt(i-1));
                i--;
                j--;
            } else if (dp[i][j] == dp[i-1][j]) {
                i--;
            } else {
                j--;
            }
        }
        return res.reverse().toString();
    }

}
