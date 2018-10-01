package DPII;

import java.util.ArrayList;
import java.util.List;

public class EditDistance {


    // DP[i][j]: the edit distance between s1[0: i) and s2[0: j), so i and j are both the length
    // base case: DP[0][j] = j, DP[i][0] = i;
    // induction rule: DP[i][j] = DP[i-1][j-1]      if s1.charAt(i-1) == s2.charAt(j-1)
    //                          = MIN { DP[i-1][j-1], DP[i][j-1], DP[i-1][j] } + 1      otherwise
    public int getEditDistance(String s1, String s2) {
        // X  X
        // X  Y
        int[] prev = new int[s2.length() + 1]; // i = 0
        for (int j = 0; j <= s2.length(); j++) {
            prev[j] = j;
        }
        int[] cur = new int[s2.length() + 1];
        for (int i = 1; i <= s1.length(); i++) {
            cur[0] = i;
            for (int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i-1) == s2.charAt(j-1)) {
                    cur[j] = prev[j-1];
                } else {
                    cur[j] = Math.min(Math.min(prev[j-1], prev[j]), cur[j-1]) + 1;
                }
            }
            int[] tmp = prev;
            prev = cur;
            cur = tmp;
        }
        return prev[s2.length()];
    }


    public List<String> getPath(String source, String target) {
        int m = source.length();
        int n = target.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int j = 1; j <= n; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (source.charAt(i-1) == target.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i-1][j-1], dp[i-1][j]), dp[i][j-1]) + 1;
                }
            }
        }
        //System.out.println(dp[m][n]);

        //     0  g  o  o  d
        //  0  0  1  2  3  4
        //  g  1  0  1  2  3
        //  o  2  1  0  1  2
        //  d  3  2  1  1  1
        List<String> results = new ArrayList<>();
        results.add(target);
        int i = m;
        int j = n;
        while (!(i == 0 && j == 0)) {
            if (i > 0 && j > 0 && source.charAt(i-1) == target.charAt(j-1)) {
                i--;
                j--;
            } else {
                if (i > 0 && j > 0 && dp[i - 1][j - 1] + 1 == dp[i][j]) {
                    // replace
                    results.add(target.substring(0, j-1) + source.charAt(i - 1) + target.substring(j));
                    i--;
                    j--;
                } else if (i > 0 && dp[i - 1][j] + 1 == dp[i][j]) {
                    // delete the i-th character in source
                    // from (i-1, j) -> (i, j) delete the i-th character in source
                    // source [0, i) + i + (i, m)
                    // target [0, j) + [j, n)
                    results.add( target.substring(0, j-1) + target.substring(j));
                    i--;
                } else {
                    // add
                    // from (i, j-1) -> (i, j) add a character at index i in source, to make it same as target[0, j)
                    // so
                    results.add(target.substring(0, j-1) + source.charAt(i - 1) + target.substring(j));
                    j--;
                }
            }
        }
        return results;
    }

    public static void main(String[] args) {
        EditDistance instance = new EditDistance();
        System.out.println(instance.getPath("good", "goo"));
    }
}
